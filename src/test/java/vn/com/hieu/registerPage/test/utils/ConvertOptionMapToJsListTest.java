package vn.com.hieu.registerPage.test.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import vn.com.hieu.registerPage.utils.CommonUtils;

@RunWith(value = Parameterized.class)
public class ConvertOptionMapToJsListTest {

	private Map<String, String> map;
	private String expected;

	public ConvertOptionMapToJsListTest(Map<String, String> map, String expected) {
		super();
		this.map = map;
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Map<String, String> map1 = new HashMap<>();
		map1.put("1", "one");
		Map<String, String> map2 = new HashMap<>();
		map2.put("1", "one");
		map2.put("2", "two");
		return Arrays.asList(new Object[][] {
			{map1, "[{\"name\":\"1\",\"value\":\"one\"}]"},
			{map2, "[{\"name\":\"1\",\"value\":\"one\"},{\"name\":\"2\",\"value\":\"two\"}]"}
		});
	}
	
	@Test
	public void testConvert() {
		assertThat(CommonUtils.convertOptionMapToJsList(map), is(expected));
	}
	
}
