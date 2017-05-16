package com.iset.secoursEJB.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iset.secoursEJB.interfaces.ClientImpl;
import com.iset.secoursEJB.interfaces.ClientLocal;
import com.iset.secoursEJB.entities.Client;


@Stateless
public class ClientDAO implements ClientImpl,ClientLocal{

	
	@PersistenceContext(unitName="secoursEJB")
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientDAO other = (ClientDAO) obj;
		if (entityManager == null) {
			if (other.entityManager != null)
				return false;
		} else if (!entityManager.equals(other.entityManager))
			return false;
		return true;
	}
	
	
	//afficher liste des clients
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> findAll() {

		Query query=getEntityManager().createQuery("select c from Client c");
		return query.getResultList();
	}
	
	
	
	//chercher client par cin
	@Override
	public Client findClient(String NID) {
		Client client =null;
			 //getSingleResult() 
			  //Execute a SELECT query that returns a single untyped result.
			client = (Client) getEntityManager().createQuery(
					 " SELECT c "
					+" FROM Client c "
					+" WHERE c.NID=:NID").setParameter("NID", NID).getSingleResult();
		if (client!= null)
			  { 
		    	   System.out.println("client trouvé par NID(ClientDAO)");
			   	   return client;
		      }		       
		
		
		return null;
	 
	}
	
	
	@Override
	public Client findByCode(int codeClient) {
		return getEntityManager().find(Client.class, codeClient);
		
	}
	
	
	
	
	
	
	
	
	
		
		
}
