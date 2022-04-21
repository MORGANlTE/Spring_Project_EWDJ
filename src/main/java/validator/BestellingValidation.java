package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.Bestelling;

public class BestellingValidation implements Validator{

	private int minTickets = 1;
	private int maxTickets = 25;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Bestelling.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Bestelling bestelling = (Bestelling) target;
		if (bestelling.getTicketAantal() > maxTickets)
		{
			errors.rejectValue("ticketAantal", "notValid.bestelling.tooMany","aantal tickets moet kleiner zijn of gelijk aan 25");
		}
		else if (bestelling.getTicketAantal() < minTickets)
		{
			errors.rejectValue("ticketAantal", "notValid.bestelling.tooLittle","aantal tickets moet groter zijn of gelijk aan 1");
		}
		if (bestelling.getVoetbalCode1() >= bestelling.getVoetbalCode2())
		{
			errors.rejectValue("voetbalCode1", "notValid.bestelling.voetbalcodes","voetbalcode1 moet kleiner zijn dan voetbalcode2");
		}
		
	}

}