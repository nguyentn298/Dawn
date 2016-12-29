package com.dante.controller.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ClientValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object object, Errors errors) {
		
	}


}
