package com.iset.secoursEJB.dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iset.secoursEJB.entities.Agence;
import com.iset.secoursEJB.interfaces.AgenceImpl;

@SuppressWarnings("serial")
@Stateless
public class AgenceDAO implements AgenceImpl ,Serializable{
	@PersistenceContext(unitName="secoursEJB")
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Agence> findAll() {
		Query query=getEntityManager().createQuery("select a from Agence a");
		return query.getResultList();
	}
	
}
