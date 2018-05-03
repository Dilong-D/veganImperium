package pl.markowski.veganImperium.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.markowski.veganImperium.storage.Avalibility;
import pl.markowski.veganImperium.storage.AvalibilityRepository;

@Component
public class AvalibilityValidator implements Validator {
	@Autowired
	private AvalibilityRepository avalibilityRepository;

	@Override
	public boolean supports(Class<?> aClass) {
		return Avalibility.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Avalibility aval = (Avalibility) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productId", "NotEmpty", "To pole jest wymagane.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shopId", "NotEmpty", "To pole jest wymagane.");
		if (avalibilityRepository.findByProductIdAndShopId(aval.getProductId(), aval.getShopId()) != null)
			errors.rejectValue("productId", "Duplicate.productForm.barcode", "Taka dosępność już istnieje.");

	}
}
