package com.iset.secoursEJB.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iset.secoursEJB.entities.Versement;
import com.iset.secoursEJB.interfaces.VersementImpl;

@Stateless
public class VersementDAO implements VersementImpl {

	@PersistenceContext(unitName="secoursEJB")
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



	@Override
	public boolean insertVersement(Versement Versement) {
		try
		{
		   getEntityManager().persist(Versement);
		   return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Versement> findAll() {
		Query query=getEntityManager().createQuery("select v from Versement v");
		return query.getResultList();
	}
}
