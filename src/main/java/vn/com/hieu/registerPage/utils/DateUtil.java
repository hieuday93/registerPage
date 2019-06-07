package vn.com.hieu.registerPage.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static final String VN_DATE_FORMAT = "dd/MM/yyyy";
	
	public static String toVnDateTimeStyle(Date date) {
		DateFormat format = new SimpleDateFormat(VN_DATE_FORMAT);
		return format.format(date);
	}
	
}
