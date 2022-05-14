package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import domain.Wedstrijd;
@Repository("wedstrijdDao")
public class JpaWedstrijdDao extends GenericDaoJpa<Wedstrijd> implements WedstrijdDao {

	@PersistenceContext
	private EntityManager em;
	
	public JpaWedstrijdDao() {
		super(Wedstrijd.class);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Wedstrijd> getWedstrijdByStadium(String stadium)
	{
		TypedQuery<Wedstrijd> queryWedstrijd = em.createNamedQuery("Wedstrijd.getWedstrijdByStadium", 
				Wedstrijd.class
				);
		queryWedstrijd.setParameter("nameStadium", stadium);
		return queryWedstrijd.getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<String> getStadiums() {
		TypedQuery<String> queryWedstrijd = em.createNamedQuery("Wedstrijd.getStadiums"
				, String.class
				);

		return queryWedstrijd.getResultList();
	}


}
