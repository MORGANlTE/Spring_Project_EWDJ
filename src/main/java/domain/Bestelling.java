package domain;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import validator.ValidEmail;

public class Bestelling {

    @NumberFormat(style = Style.NUMBER)//enkel nummers...
    private int ticketAantal = 1;
    
    @NumberFormat(style = Style.NUMBER)
    private int voetbalCode1 = 10;
    
    @NumberFormat(style = Style.NUMBER)
    private int voetbalCode2 = 20;

    //moet ingevuld zijn
    //moet geldige email zijn
    @ValidEmail
    private String email;


    public int getTicketAantal() {
        return ticketAantal;
    }

    public String getEmail() {
        return email;
    }
    
    public int getVoetbalCode1() {
        return voetbalCode1;
    }
    
    public int getVoetbalCode2() {
        return voetbalCode2;
    }
    


    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setTicketAantal(int aantal) {
    	ticketAantal = aantal;
    }
    
    public void setVoetbalCode1(int aantal) {
        voetbalCode1 = aantal;
    }
    
    public void setVoetbalCode2(int aantal) {
        voetbalCode2 = aantal;
    }


    
}