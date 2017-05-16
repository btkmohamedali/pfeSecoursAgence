package com.iset.secoursEJB.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iset.secoursEJB.entities.Retrait;
import com.iset.secoursEJB.interfaces.RetraitImpl;
@Stateless
public class RetraitDAO implements RetraitImpl{

	@PersistenceContext(unitName="secoursEJB")
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public boolean insertRetrait(Retrait Retrait) {
		try
		{
		   getEntityManager().persist(Retrait);
		   return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Retrait> findAll() {
		Query query=getEntityManager().createQuery("select r from Retrait r");
		return query.getResultList();
	}
	@Override
	public long countNbrRetrait()
	{
		long count;
		 count=(long) getEntityManager().createQuery("select count(r) from Retrait r").getSingleResult();
		return count;
	}
}
