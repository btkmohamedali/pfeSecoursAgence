package com.iset.secoursEJB.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iset.secoursEJB.interfaces.RemiseEffetImpl;
import com.iset.secoursEJB.dao.RemiseEffetDAO;
import com.iset.secoursEJB.entities.RemiseEffet;

@Stateless
public class RemiseEffetDAO implements RemiseEffetImpl{

	@PersistenceContext(unitName="secoursEJB")
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public boolean insertRemiseEffet(RemiseEffet remiseEffet) {
		
		try
		{
		   getEntityManager().persist(remiseEffet);
		   return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RemiseEffet> findAll() {
		Query query=getEntityManager().createQuery("select r from RemiseEffet r");
		return query.getResultList();
	}
	

	@Override
	public int countNbrRemiseE()
	{
		
		int count=(int) getEntityManager().createQuery("select count(r) from RemiseEffet r").getSingleResult();
		return count;
	}
	
	
}
