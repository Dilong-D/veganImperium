package pl.markowski.veganImperium.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.markowski.veganImperium.storage.Role;
import pl.markowski.veganImperium.storage.User;

@Component
public class NewUserValidator implements Validator {
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty", "To pole jest wymagane.");
		if (user.getUsername().length() < 6 || user.getUsername().length() > 32)
			errors.rejectValue("username", "Size.userForm.username", "Login powinien składać się z 8-32 znaków.");

		if (userService.findByUsername(user.getUsername()) != null)
			errors.rejectValue("username", "Duplicate.userForm.username", "Użytkownik o podanym loginie już istnieje.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty", "To pole jest wymagane.");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32)
			errors.rejectValue("password", "Size.userForm.password", "Hasło powinno składać się z 8-32 znaków.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "NotEmpty", "To pole jest wymagane.");
		if (!user.getRole().equals(Role.ADMIN) && !user.getRole().equals(Role.MODERATOR))
			errors.rejectValue("role", "Value.userForm.role", "Nieprawidłowa rola.");
	}
}
