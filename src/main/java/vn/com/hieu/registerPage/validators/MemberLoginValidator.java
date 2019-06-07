package vn.com.hieu.registerPage.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vn.com.hieu.registerPage.models.MemberLoginModel;

@Component
public class MemberLoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberLoginModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "member.username.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "member.password.empty");
	}

}
