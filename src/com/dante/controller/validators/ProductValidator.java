package com.dante.controller.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductValidator.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productId", "dawm.product.id.missing", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "dawm.product.name.missing", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productQuantity", "dawm.product.quantity.missing", "Field name is required.");
		
	}

}
