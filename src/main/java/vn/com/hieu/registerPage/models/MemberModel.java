package vn.com.hieu.registerPage.models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import vn.com.hieu.registerPage.utils.CommonUtils;
import vn.com.hieu.registerPage.utils.DateUtil;

public class MemberModel {

	private long memberId;
	private String mobileNumber;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private int gender;
	private String email;
	private int dobDay;
	private int dobMonth;
	private int dobYear;
	private Map<Integer, String> daysOfMonth;
	private Map<Integer, String> monthsOfYear;
	private Map<Integer, String> years;
	
	public MemberModel() {

		gender = 1;
		
		initDOBSelect();

	}

	public MemberModel(boolean isInitDOB) {

		gender = 1;

		if (isInitDOB) {
			initDOBSelect();
		}
	}

	private void initDOBSelect() {
		daysOfMonth = new HashMap<>(32, 1);
		for (int i = 0; i < 32; i++) {
			if (i == 0) {
				daysOfMonth.put(0, "Date");
				continue;
			}
			daysOfMonth.put(i, Integer.toString(i));
		}

		monthsOfYear = new HashMap<>(13, 1);
		for (int i = 0; i < 13; i++) {
			switch (i) {
			case 0:
				monthsOfYear.put(0, "Month");
				break;
			case 1:
				monthsOfYear.put(1, "January");
				break;
			case 2:
				monthsOfYear.put(2, "February");
				break;
			case 3:
				monthsOfYear.put(3, "March");
				break;
			case 4:
				monthsOfYear.put(4, "April");
				break;
			case 5:
				monthsOfYear.put(5, "May");
				break;
			case 6:
				monthsOfYear.put(6, "June");
				break;
			case 7:
				monthsOfYear.put(7, "July");
				break;
			case 8:
				monthsOfYear.put(8, "August");
				break;
			case 9:
				monthsOfYear.put(9, "Septempber");
				break;
			case 10:
				monthsOfYear.put(10, "October");
				break;
			case 11:
				monthsOfYear.put(11, "November");
				break;
			case 12:
				monthsOfYear.put(12, "December");
				break;
			default:
				break;
			}
		}

		years = new HashMap<Integer, String>();
		years.put(0, "Year");
		for (int i = 1920; i < Calendar.getInstance().get(Calendar.YEAR) + 1; i++) {
			years.put(i, Integer.toString(i));
		}
	}

	public MemberModel(long memberId, String mobileNumber, String firstName, String lastName, Date dateOfBirth,
			int gender, String email) {
		super();
		this.memberId = memberId;
		this.mobileNumber = mobileNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.email = email;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDobDay() {
		return dobDay;
	}

	public void setDobDay(int dobDay) {
		this.dobDay = dobDay;
	}

	public int getDobMonth() {
		return dobMonth;
	}

	public void setDobMonth(int dobMonth) {
		this.dobMonth = dobMonth;
	}

	public int getDobYear() {
		return dobYear;
	}

	public void setDobYear(int dobYear) {
		this.dobYear = dobYear;
	}

	public Map<Integer, String> getDaysOfMonth() {
		return daysOfMonth;
	}

	public void setDaysOfMonth(Map<Integer, String> daysOfMonth) {
		this.daysOfMonth = daysOfMonth;
	}

	public Map<Integer, String> getMonthsOfYear() {
		return monthsOfYear;
	}

	public void setMonthsOfYear(Map<Integer, String> monthsOfYear) {
		this.monthsOfYear = monthsOfYear;
	}

	public Map<Integer, String> getYears() {
		return years;
	}

	public void setYears(Map<Integer, String> years) {
		this.years = years;
	}

	public String getDateOfBirthStr() {
		if (dateOfBirth == null) {
			return "";
		}
		return DateUtil.toVnDateTimeStyle(dateOfBirth);
	}

	public String getGenderStr() {
		if (gender == 0) {
			return "Female";
		}
		return "Male";
	}

	public String getDaysOfMonthJsStr() {
		return CommonUtils.convertOptionMapToJsList(daysOfMonth);
	}

	public void constructDateOfBirth() {
		if ((dobDay > 0) && (dobMonth > 0) && (dobYear > 0)) {
			LocalDate d = LocalDate.of(dobYear, dobMonth, dobDay);
			dateOfBirth = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
	}

}
