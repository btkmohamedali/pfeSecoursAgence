package com.iset.secoursEJB.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.iset.secoursEJB.entities.Caisse;
import com.iset.secoursEJB.entities.Client;
@Entity
public class Agence implements Serializable {

	private static final long serialVersionUID = 1L;

	private int codeAgence;
	@NotNull
	private String libelle_Agence;
	//relation client agence 
	private List<Client> clients;
	
	//relation agence caisse
	private List<Caisse> caisses;
	
	

	public Agence() 
	{
		
	}
	
	@Id
	public int getCodeAgence() {
		return codeAgence;
	}
	public void setCodeAgence(int codeAgence) {
		this.codeAgence = codeAgence;
	}
	
	
	
	
	@OneToMany(mappedBy="agence")
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	@OneToMany(mappedBy="agence")
	public List<Caisse> getCaisses() {
		return caisses;
	}
	public void setCaisses(List<Caisse> caisses) {
		this.caisses = caisses;
	}

	public String getLibelle_Agence() {
		return libelle_Agence;
	}

	public void setLibelle_Agence(String libelle_Agence) {
		this.libelle_Agence = libelle_Agence;
	}

	

	
   
}