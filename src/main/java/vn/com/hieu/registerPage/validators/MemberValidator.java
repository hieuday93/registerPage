package vn.com.hieu.registerPage.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vn.com.hieu.registerPage.models.MemberModel;

@Component
public class MemberValidator implements Validator {

	public static final Pattern PHONE_REGEX = Pattern.compile("^((\\(\\+\\d{2,3}\\))|(\\+\\d{2,3}))?\\s?\\d{9,10}$");

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "member.firstName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "member.lastName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "member.mobileNumber.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "member.email.empty");

		if (!errors.hasErrors()) {
			MemberModel memberModel = (MemberModel) target;
			if (memberModel.getFirstName().length() > 255) {
				errors.rejectValue("firstName", "member.firstName.tooLong");
			}

			if (memberModel.getLastName().length() > 255) {
				errors.rejectValue("lastName", "member.lastName.tooLong");
			}

			if (memberModel.getMobileNumber().length() > 20) {
				errors.rejectValue("mobileNumber", "member.mobileNumber.tooLong");
			}

			Matcher matcher = PHONE_REGEX.matcher(memberModel.getMobileNumber());
			if (!matcher.matches()) {
				errors.rejectValue("mobileNumber", "member.mobileNumber.notValid");
			}

			if (memberModel.getEmail().length() > 255) {
				errors.rejectValue("email", "member.email.tooLong");
			}
		}

	}

}
