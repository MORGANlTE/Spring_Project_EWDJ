package domain;
//Een wedstrijd
public class Wedstrijd {

    private String id; //unieke sleutel

    private String[] landen; //2 landen van de wedstrijd

    private int dag; //dag van de wedstrijd

    private int uur; //uur van de wedstrijd

    public Wedstrijd() {
    }

    public Wedstrijd(String id, String[] landen, int dag, int uur) {
        this.id = id;
        this.landen = landen;
        this.dag = dag;
        this.uur = uur;
    }

    public String getId() {
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
}
