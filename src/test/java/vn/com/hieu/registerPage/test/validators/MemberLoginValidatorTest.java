package vn.com.hieu.registerPage.test.validators;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.validation.BeanPropertyBindingResult;

import vn.com.hieu.registerPage.models.MemberLoginModel;
import vn.com.hieu.registerPage.validators.MemberLoginValidator;

@RunWith(value = Parameterized.class)
public class MemberLoginValidatorTest {

	private MemberLoginModel model;
	private boolean expected;

	public MemberLoginValidatorTest(MemberLoginModel model, boolean expected) {
		super();
		this.model = model;
		this.expected = expected;
	}
	
	@Parameters
	public static Collection<Object[]> data() {
		MemberLoginModel model1 = new MemberLoginModel();
		model1.setUsername("hieu");
		model1.setPassword("123456");
		MemberLoginModel model2 = new MemberLoginModel();
		model2.setPassword("123456");
		MemberLoginModel model3 = new MemberLoginModel();
		model3.setUsername("hieu");
		MemberLoginModel model4 = new MemberLoginModel();
		
		return Arrays.asList(new Object[][] {
			{model1, false},
			{model2, true}, 
			{model3, true}, 
			{model4, true}
		});
	}
	
	@Test
	public void testValidator() {
		MemberLoginValidator validator = new MemberLoginValidator();
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(model, "model");
		validator.validate(model, result);
		assertThat(result.hasErrors(), is(expected));
	}

}
