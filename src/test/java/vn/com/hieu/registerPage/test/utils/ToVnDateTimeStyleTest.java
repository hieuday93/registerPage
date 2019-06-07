package vn.com.hieu.registerPage.test.utils;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import vn.com.hieu.registerPage.utils.DateUtil;

@RunWith(value = Parameterized.class)
public class ToVnDateTimeStyleTest {

	private Date date;
	private String expected;

	public ToVnDateTimeStyleTest(Date date, String expected) {
		super();
		this.date = date;
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2019, 1, 21);
		Date d1 = calendar.getTime();
		calendar.set(1993, 11, 30, 10, 10);
		Date d2 = calendar.getTime();
		calendar.set(1010, 9, 21, 20, 10, 10);
		Date d3 = calendar.getTime();
		
		return Arrays.asList(new Object[][] {
			{d1, "21/02/2019"},
			{d2, "30/12/1993"}, 
			{d3, "21/10/1010"}
		});
	}
	
	@Test
	public void testDateTime() {
		assertThat(DateUtil.toVnDateTimeStyle(date), is(expected));
	}
	
}
