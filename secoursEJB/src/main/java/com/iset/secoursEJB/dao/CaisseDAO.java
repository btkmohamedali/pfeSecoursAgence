package com.iset.secoursEJB.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.iset.secoursEJB.entities.Caisse;
import com.iset.secoursEJB.interfaces.CaisseImpl;


@Stateless
public class CaisseDAO implements CaisseImpl{

	@PersistenceContext(unitName="secoursEJB")
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public double versementCaisse(String login,double solde  , double montant) {
		Caisse c=new Caisse();
		c=findCaisseByLogin(login);
		System.out.println("code caisse="+c.getCodecaisse());
		System.out.println(montant);
		return c.getSoldeCaisse()+montant;
	}
	@Override
	public double retraitCaisse(String login,double solde  , double montant) {
		Caisse c=new Caisse();
		c=findCaisseByLogin(login);
		System.out.println("code caisse="+c.getCodecaisse());
		System.out.println(montant);
		return c.getSoldeCaisse()- montant ;
	}
	@Override
	public Caisse updateCaisse(Caisse c) {
		c=getEntityManager().merge(c);
		System.out.println("Caisse updated");
		return c;
	}

	
	@Override
	public Caisse findCaisseByLogin(String login) {
		Caisse Caisse =null;
			 //getSingleResult() 
			  //Execute a SELECT query that returns a single untyped result.
			Caisse = (Caisse) getEntityManager().createQuery(
					 " SELECT c "
					+" FROM Caisse c "
					+" WHERE c.utilisateur.login=:login").setParameter("login", login).getSingleResult();
		if (Caisse!= null)
			  { 
		    	   System.out.println("Caisse trouvé par login(CaisseDAO)");
			   	   return Caisse;
		      }		       
		
		
		return null;
	 
	}
	@Override
	public Caisse findByCode(int codecaisse) {
		return getEntityManager().find(Caisse.class, codecaisse);

	}
}
