package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

public class GenericDaoJpa<T> implements GenericDao<T> {

	private Class<T> type;
	protected EntityManager em;
	public GenericDaoJpa(Class<T> type) {
		super();
		this.type = type;
	}

	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Transactional(readOnly = true)
	public T get(Long id) {
		T entity = this.em.find(this.type, id);
		return entity;
	}
	
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return this.em.createQuery("select entity from " + this.type.getName() + " entity").getResultList();
	}
	
	@Override
	@Transactional
	public void insert(T object) {
		em.persist(object);
	}
	
	@Override
	@Transactional
	public void delete(T object) {
		em.remove(em.merge(object));
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean exists(Long id) {
		T entity = this.em.find(this.type, id);
		return entity != null;
	}
	
	@Override
	@Transactional
	public T update(T object) {
		return em.merge(object);
	}
}
