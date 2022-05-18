package domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

//Een wedstrijd

@Entity
@NamedQueries({
	@NamedQuery(name = "Wedstrijd.findAll",
			query="SELECT w FROM Wedstrijd w ORDER BY w.id"
	),
	
	@NamedQuery(name = "Wedstrijd.getWedstrijdByStadium",
	query="SELECT w FROM Wedstrijd w WHERE w.stadium = :nameStadium ORDER BY w.id"
	),
	
	@NamedQuery(name = "Wedstrijd.getWedstrijdById",
	query="SELECT w FROM Wedstrijd w WHERE w.id = :id"
	),
	
	
	@NamedQuery(name ="Wedstrijd.getStadiums",
	query = "SELECT DISTINCT stadium FROM Wedstrijd w"
	),
	
	@NamedQuery(name ="Wedstrijd.getStadiumByWedstrijdId",
	query = "SELECT stadium FROM Wedstrijd w WHERE w.id = :id"
	)
})
public class Wedstrijd implements Serializable{
	//standaard als serializable
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; //unieke sleutel

    private String[] landen; //2 landen van de wedstrijd

    private int dag; //dag van de wedstrijd

    private int uur; //uur van de wedstrijd
    
    private String stadium;
    
    private int tickets;

    public Wedstrijd() {
    }

    public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public int getTickets() {
		return tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

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
		Wedstrijd other = (Wedstrijd) obj;
		return Objects.equals(id, other.id);
	}

	public Wedstrijd(Long id, String[] landen, int dag, int uur, String stadium, int tickets) {
        this.id = id;
        this.landen = landen;
        this.dag = dag;
        this.uur = uur;
        this.stadium = stadium;
        this.tickets = tickets;
        		
    }
	
	public Wedstrijd(String[] landen, int dag, int uur, String stadium, int tickets) {
        this.landen = landen;
        this.dag = dag;
        this.uur = uur;
        this.stadium = stadium;
        this.tickets = tickets;
        		
    }

    public Long getId() {
        return id;
    }

    public String[] getLanden() {
        return landen;
    }

    public int getDag() {
        return dag;
    }

    public int getUur() {
        return uur;
    }
    
    @Override
    public String toString()
    {
        return String.format("%s vs %s op %d-11", landen[0], landen[1], dag); 
    }
    
    public boolean uitverkocht() {
        return tickets == 0;
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
}
