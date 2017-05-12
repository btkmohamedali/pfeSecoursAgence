package com.iset.secoursEJB.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iset.secoursEJB.interfaces.OperationImpl;
import com.iset.secoursEJB.entities.Operation;

@Stateless
public class OperationDAO implements OperationImpl {

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
	public List<Operation> findAll() {
		Query query=getEntityManager().createQuery("select o from Operation o Where o.codeoperation=1 or o.codeoperation=2 ");
		return query.getResultList();
	}

	@Override
	public Operation findByCode(int codeop) {
		
		return getEntityManager().find(Operation.class, codeop);
	}

	@Override
	public boolean insertOperation(Operation operation) {
		try
		{
		   getEntityManager().persist(operation);
		   return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}

	
	

	
	
}
