package com.example.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.repositories.entities.User;

@Component("beforeCreateUserValidator")
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		
		if (StringUtils.isBlank(user.getFirstName())) {
			errors.rejectValue("firstName", "firstName.empty");
		}
		
		if (StringUtils.isBlank(user.getLastName())) {
			errors.rejectValue("lastName", "lastName.empty");
		}

		if (user.getBirthDate() == null) {
			errors.rejectValue("birthDate", "birthDate.empty");
		}
		
		if (user.getCreatedAt() != null) {
			errors.rejectValue("createdAt", "createdAt.notEmpty");
		}
	}
	
}
