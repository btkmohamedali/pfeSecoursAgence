package com.iset.secoursEJB.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iset.secoursEJB.entities.Virement;
import com.iset.secoursEJB.interfaces.VirementImpl;

@Stateless
public class VirementDAO implements VirementImpl {

	@PersistenceContext(unitName="secoursEJB")
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) 
	{
		this.entityManager = entityManager;
	}



	@Override
	public boolean insertVirement(Virement virement) {
		try
		{
		   getEntityManager().persist(virement);
		   return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Virement> findAll() {
		Query query=getEntityManager().createQuery("select v from Virement v");
		return query.getResultList();
	}
	
	@Override
	public long countNbrVirement()
	{
		
		long count=(long) getEntityManager().createQuery("select count(v) from Virement v").getSingleResult();
		return count;
	}
}

