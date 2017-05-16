package com.iset.secoursEJB.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	public double versementCaisse(int numcaisse,String login,double solde  , double montant) {
		Caisse c=new Caisse();
		c=findCaisseByLoginCode(login,numcaisse);
		System.out.println("num caisse="+c.getNumerocaisse());
		System.out.println(montant);
		return c.getSoldeCaisse()+ montant;
	}
	@Override
	public double retraitCaisse(int numcaisse,String login,double solde  , double montant) {
		Caisse c=new Caisse();
		c=findCaisseByLoginCode(login,numcaisse);
		System.out.println("num caisse="+c.getNumerocaisse());
		System.out.println(montant);
		return c.getSoldeCaisse()- montant ;
	}
	@Override
	public Caisse updateCaisse(Caisse c) {
		c=getEntityManager().merge(c);
		System.out.println("Caisse updated="+c.getNumerocaisse());
		return c;
	}

	
	
	
	@Override
	public Caisse findByCode(int numcaisse) {
		return getEntityManager().find(Caisse.class, numcaisse);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Caisse> findAllCaisse() {
		Query query=getEntityManager().createQuery("select c from Caisse c");
		return query.getResultList();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Caisse> findListCaisseByLogin(String login) {
		System.out.println("login"+login);
		String requete=" SELECT c "
				+ " FROM Caisse c "
				+" WHERE c.utilisateur.login=:login";
		Query query=getEntityManager().createQuery(requete);
		query.setParameter("login", login);
		return  query.getResultList();	
	}
	
	@Override
	public Caisse findCaisseByLoginCode(String login,int code) {
		System.out.println("login"+login);
		String requete=" SELECT c "
				+ " FROM Caisse c "
				+" WHERE c.utilisateur.login=:login"
				+" AND c.numerocaisse=:code";
		Caisse c=(Caisse) getEntityManager().createQuery(requete).setParameter("login", login).setParameter("code", code).getSingleResult();
		return  c;	
	}
}
