package validator;

import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.Bestelling;

public class BestellingValidation implements Validator{
	
	//gaat alles controleren
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Bestelling.class.isAssignableFrom(clazz);
	}

	private String vb1 = "voetbalCode1";
	private String vb2 = "voetbalCode2";
	private String ta = "ticketAantal";
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Bestelling bestelling = (Bestelling) target;
		//voetbalcode1
		try {
			if(bestelling.getVoetbalCode1().isEmpty())
				errors.rejectValue(vb1,"validation.voetbalCode1.NotEmpty.message", "not empty");
			else if (isNumeric(bestelling.getVoetbalCode1()))
			{
				if (Integer.parseInt(bestelling.getVoetbalCode1()) >= Integer.parseInt(bestelling.getVoetbalCode2()))
				{
					errors.rejectValue(vb1, "validation.voetbalCode1.teGroot.message", "too high");
				}
			}
			else{
				errors.rejectValue(vb1, "validation.voetbalCode1.NaN.message","no text");
			}
		} catch(NumberFormatException nfe)
		{
			
		}
		
		//voetbalcode2
		try {
			if (bestelling.getVoetbalCode2().isEmpty())
			{
				errors.rejectValue(vb2, "validation.voetbalCode2.NotEmpty.message", "not empty");
			}
			else {
				int vc2 = Integer.parseInt(bestelling.getVoetbalCode2());
			}
		} catch(NumberFormatException nfe)
		{
			errors.rejectValue(vb2, "validation.voetbalCode2.NaN.message", "no text");
		}
		
		//ticketaantal
		try {
			if (bestelling.getTicketAantal().isBlank())
				errors.rejectValue(ta, "validation.aantalTickets.NotEmpty.message", "not empty");
			//als geen nummers?
			else if (Integer.parseInt(bestelling.getTicketAantal()) > bestelling.getMaxAantalTickets())
			{
				Locale locale = new Locale("nl");
				errors.rejectValue(ta, "" + bestelling.getMinAantalTickets(), messageSource.getMessage("validation.aantalTickets.TooMany.message", null, locale) + " " + bestelling.getMaxAantalTickets());
			}
			else if (Integer.parseInt(bestelling.getTicketAantal()) < bestelling.getMinAantalTickets())
			{
				Locale locale = new Locale("nl");
				errors.rejectValue(ta, "" + bestelling.getMinAantalTickets(), messageSource.getMessage("validation.aantalTickets.TooLess.message", null, locale) + " " + bestelling.getMinAantalTickets());
			}
		} catch(NumberFormatException nfe)
		{
			//vertaling niet mogelijk...
			errors.rejectValue(ta, "{validation.voetbalCode1.NaN.message}", "No text");
		}
		
		
	}

}