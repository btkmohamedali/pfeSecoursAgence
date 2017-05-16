package com.iset.secoursEJB.dao;
import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iset.secoursEJB.interfaces.CompteImpl;
import com.iset.secoursEJB.entities.Compte;

@Stateless
public class CompteDAO  implements CompteImpl,Serializable{

	private static final long serialVersionUID = 1L;
	
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
		CompteDAO other = (CompteDAO) obj;
		if (entityManager == null) {
			if (other.entityManager != null)
				return false;
		} else if (!entityManager.equals(other.entityManager))
			return false;
		return true;
	}
	
	
	
	//afficher liste des comptes
	@SuppressWarnings("unchecked")
	@Override
	public List<Compte> selectAll() {
		Query query=getEntityManager().createQuery("select c from Compte c");
		return query.getResultList();
	}
	
	
	//afficher list des comptes dun client par son cin
	@SuppressWarnings("unchecked")
	@Override
	public List<Compte> findListCompteByCINClient(String NID) {
		String requete=" SELECT c "
				+ " FROM Compte c , Client clt"
				+ " WHERE  clt=c.client "
				+ " AND clt.NID=:NID";
		Query query=getEntityManager().createQuery(requete);
		query.setParameter("NID", NID);
		return  query.getResultList();	
	}
	
	
	//chercher le solde du compte
	@Override
	public double findSoldeCompte(int code) {
		String
		requeteSelect="SELECT c.solde"
				+ " FROM Compte c "
				+ " WHERE c.codeCompte=:code";
		try 
		{
		double c=  (double) getEntityManager().createQuery(requeteSelect).setParameter("code",code).getSingleResult();
		
		 return c;
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("erreur");
			return 0;
		}
	}
	
	//chercher compte a partir du numero de compte 
	@Override
	public Compte findByCode(long numCompte) {
		
		return getEntityManager().find(Compte.class, numCompte);
	}
	
	
	@Override
	public double versement(long numCompte, double solde, double montant) {
		Compte c=new Compte();
		c=findByCode(numCompte);
		return c.getSolde()+montant;
	}
	@Override
	public double retrait(long numCompte, double solde, double montant) {
		Compte c=new Compte();
		c=findByCode(numCompte);
		System.out.println("solde="+solde);
		System.out.println(montant);
		return c.getSolde()- montant ;
	}
	@Override
	public Compte updateCompte(Compte c) {
		c=getEntityManager().merge(c);
		System.out.println("compte updated");
		return c;
	}
	
	
	
	
	
	
}
