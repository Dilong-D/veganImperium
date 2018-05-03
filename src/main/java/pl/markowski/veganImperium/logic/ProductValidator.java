package pl.markowski.veganImperium.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.markowski.veganImperium.storage.Product;

@Component
public class ProductValidator implements Validator {
	@Autowired
	private ProductService productService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Product.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Product product = (Product) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "barcode", "NotEmpty", "To pole jest wymagane.");
		if (productService.getProductByBarcode(product.getBarcode()) != null)
			errors.rejectValue("barcode", "Duplicate.productForm.barcode",
					"Produkt o podanym kodzie kreskowym już istnieje.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty", "To pole jest wymagane.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vegan", "NotEmpty", "To pole jest wymagane.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vegetarian", "NotEmpty", "To pole jest wymagane.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "palmOil", "NotEmpty", "To pole jest wymagane.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mealId", "NotEmpty", "To pole jest wymagane.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kindId", "NotEmpty", "To pole jest wymagane.");
	}
}
