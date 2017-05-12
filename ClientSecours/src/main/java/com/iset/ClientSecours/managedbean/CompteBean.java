package com.iset.ClientSecours.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.iset.secoursEJB.entities.Compte;
import com.iset.secoursEJB.interfaces.CompteImpl;




@ManagedBean(name="compteBean")
@SessionScoped
public class CompteBean implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private List<Compte> listeCompte= new ArrayList<Compte>();


	@EJB
	private CompteImpl  dao;
	
	private Compte compte=new Compte();
	

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	public List<Compte> getListeCompte() {
		return listeCompte;
	}

	public void setListeCompte(List<Compte> listeCompte) {
		this.listeCompte = listeCompte;
	}

	//afficher toute la liste des comptes
	public List<Compte> findAll()
	{
		return dao.selectAll();
	}
	
	
	
	

	

}
