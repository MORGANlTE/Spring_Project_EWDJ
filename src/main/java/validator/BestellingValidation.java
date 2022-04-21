package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.Bestelling;

public class BestellingValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Bestelling.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Bestelling acc = (Bestelling) target;
	}

}