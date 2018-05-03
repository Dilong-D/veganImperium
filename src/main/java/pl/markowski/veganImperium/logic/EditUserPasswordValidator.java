package pl.markowski.veganImperium.logic;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.markowski.veganImperium.storage.User;

@Component
public class EditUserPasswordValidator implements Validator {
	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty", "To pole jest wymagane.");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32)
			errors.rejectValue("password", "Size.userForm.password", "Hasło powinno składać się z 8-32 znaków.");

	}
}
