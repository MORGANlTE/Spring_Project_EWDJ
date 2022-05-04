package domain;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.executable.ValidateOnExecution;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import validator.ValidEmail;

public class Bestelling {
	//moet ingevuld zijn
	//moet geldige email zijn
	@NotEmpty(message="{validation.email.NotEmpty.message}")
	@ValidEmail(message="{validation.email.NotValid.message}")
	private String email;
	
	//minstens 1 max 25
	@NotEmpty(message="{validation.aantalTickets.NotEmpty.message}")
	@Pattern(message="{validation.aantalTickets.NaN.message}", regexp = "^\\d+$")
	@Min(message="{validation.aantalTickets.TooLess.message}", value = 1)
	@Max(message="{validation.aantalTickets.TooMany.message}", value = 25)
    private String ticketAantal = "1";
    
	//
	
	@NotEmpty(message="{validation.voetbalCode2.NotEmpty.message}")
	@Pattern(message="{validation.voetbalCode1.NaN.message}", regexp = "^\\d+$")
	private String voetbalCode2 = "20";
	
	@NotEmpty(message="{validation.voetbalCode1.NotEmpty.message}")
	@Pattern(message="{validation.voetbalCode1.NaN.message}", regexp = "^\\d+$")
	private String voetbalCode1 = "10";
	
	public boolean isValid()
	{
		if(Integer.parseInt(voetbalCode2) <= Integer.parseInt(voetbalCode1))
			return false;
		return true;
		
	}



    public String getTicketAantal() {
        return ticketAantal;
    }

    public String getEmail() {
        return email;
    }
    
    public String getVoetbalCode1() {
        return voetbalCode1;
    }
    
    public String getVoetbalCode2() {
        return voetbalCode2;
    }
    

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setTicketAantal(String aantal) {
    	ticketAantal = aantal;
    }
    
    public void setVoetbalCode1(String aantal) {
        voetbalCode1 = aantal;
    }
    
    public void setVoetbalCode2(String aantal) {
        voetbalCode2 = aantal;
    }


    
}