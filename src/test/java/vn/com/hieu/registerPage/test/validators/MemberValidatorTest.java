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

import vn.com.hieu.registerPage.models.MemberModel;
import vn.com.hieu.registerPage.validators.MemberValidator;

@RunWith(value = Parameterized.class)
public class MemberValidatorTest {

	private MemberModel model;
	private boolean expected;

	public MemberValidatorTest(MemberModel model, boolean expected) {
		super();
		this.model = model;
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		
		MemberModel model1 = new MemberModel(false);
		model1.setFirstName("hieu");
		model1.setLastName("nguyen");
		model1.setMobileNumber("0657823419");
		model1.setEmail("hieu@mail.com");
		MemberModel model2 = new MemberModel(false);
		model2.setLastName("nguyen");
		model2.setMobileNumber("0657823419");
		model2.setEmail("hieu@mail.com");
		MemberModel model3 = new MemberModel(false);
		model3.setFirstName("hieu");
		model3.setMobileNumber("0657823419");
		model3.setEmail("hieu@mail.com");
		MemberModel model4 = new MemberModel(false);
		model4.setFirstName("hieu");
		model4.setLastName("nguyen");
		model4.setEmail("hieu@mail.com");
		MemberModel model5 = new MemberModel(false);
		model5.setFirstName("hieu");
		model5.setLastName("nguyen");
		model5.setMobileNumber("0657823419");
		MemberModel model6 = new MemberModel(false);
		model6.setFirstName("hieu");
		model6.setLastName("nguyen");
		model6.setMobileNumber("012345619d");
		model6.setEmail("hieu@mail.com");
		MemberModel model7 = new MemberModel(false);
		model7.setFirstName("hieu");
		model7.setLastName("nguyen");
		model7.setMobileNumber("(+84) 9521384961");
		model7.setEmail("hieu@mail.com");
		
		return Arrays.asList(new Object[][] {
			{model1, false},
			{model2, true}, 
			{model3, true}, 
			{model4, true},
			{model5, true},
			{model6, true},
			{model7, false},
		});
		
	}	
	
	@Test
	public void testValidator() {
		MemberValidator validator = new MemberValidator();
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(model, "model");
		validator.validate(model, result);
		assertThat(result.hasErrors(), is(expected));
	}
	
}
