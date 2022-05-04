package validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.Bestelling;

public class BestellingValidation implements Validator{

	private int minTickets = 1;
	private int maxTickets = 25;
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Bestelling.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Bestelling bestelling = (Bestelling) target;
		try {
			if (Integer.parseInt(bestelling.getVoetbalCode1()) >= Integer.parseInt(bestelling.getVoetbalCode2()))
			{
				errors.rejectValue("voetbalCode1", "{validation.voetbalCode1.teGroot.message}","voetbalcode1 moet kleiner zijn dan voetbalcode2");
			}
		} catch(NumberFormatException nfe)
		{
			//vertaling niet mogelijk...
			errors.rejectValue("voetbalCode1", "{validation.voetbalCode1.NaN.message}","moet uit getallen bestaan");
		}
		
	}

}