package service;
import domain.Wedstrijd;
import java.util.List;

public interface WedstrijdDao extends GenericDao<Wedstrijd>{
	public List<Wedstrijd> findAll();
	public List<Wedstrijd> getWedstrijdByStadium(String stadium);
	public List<String> getStadiums();
}
