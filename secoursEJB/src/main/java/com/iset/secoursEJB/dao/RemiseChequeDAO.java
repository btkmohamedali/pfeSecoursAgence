package com.iset.secoursEJB.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iset.secoursEJB.entities.RemiseCheque;
import com.iset.secoursEJB.interfaces.RemiseChequeImpl;
@Stateless
public class RemiseChequeDAO implements RemiseChequeImpl {

	@PersistenceContext(unitName="secoursEJB")
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public boolean insertRemiseCheque(RemiseCheque remiseCheque) {
		try
		{
		   getEntityManager().persist(remiseCheque);
		   return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RemiseCheque> findAll() {
		Query query=getEntityManager().createQuery("select r from RemiseCheque r");
		return query.getResultList();
	}

	@Override
	public int countNbrRemiseC()
	{
		
		int count=(int) getEntityManager().createQuery("select count(r) from RemiseCheque r").getSingleResult();
		return count;
	}
}
