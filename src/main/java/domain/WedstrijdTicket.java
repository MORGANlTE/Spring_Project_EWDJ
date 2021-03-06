package domain;

import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Aantal tickets beschikbaar per wedstrijd
public class WedstrijdTicket{


	private Wedstrijd wedstrijd; 

    private int tickets; //aantal tickets beschikbaar

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WedstrijdTicket other = (WedstrijdTicket) obj;
		return Objects.equals(id, other.id);
	}

	public WedstrijdTicket(Wedstrijd wedstrijd, int tickets) {
        this.wedstrijd = wedstrijd;
        this.tickets = tickets;
    }

    public int getTickets() {
        return tickets;
    }
    
    public Wedstrijd getWedstrijd() {
        return wedstrijd;
    }
    
    //We willen 'aantal' tickets kopen
    public int ticketsKopen(int aantal) {
        if (aantal <= 0) {
            return -1;
        }
        
        //Nog voldoende tickets
        if (tickets >= aantal) {
            tickets -= aantal;
            return aantal;
        }

        //Niet meer voldoende tickets
        int gekocht = tickets;
        tickets = 0;
        return gekocht;
    }

    public boolean uitverkocht() {
        return tickets == 0;
    }
}
