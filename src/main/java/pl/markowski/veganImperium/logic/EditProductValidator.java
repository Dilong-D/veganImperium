package pl.markowski.veganImperium.logic;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.markowski.veganImperium.storage.Product;

@Component
public class EditProductValidator implements Validator {
	@Override
	public boolean supports(Class<?> aClass) {
		return Product.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "barcode", "NotEmpty", "To pole jest wymagane.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty", "To pole jest wymagane.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vegan", "NotEmpty", "To pole jest wymagane.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vegetarian", "NotEmpty", "To pole jest wymagane.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "palmOil", "NotEmpty", "To pole jest wymagane.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mealId", "NotEmpty", "To pole jest wymagane.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kindId", "NotEmpty", "To pole jest wymagane.");
	}
}
