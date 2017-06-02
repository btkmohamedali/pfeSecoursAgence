package com.iset.secoursEJB.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.iset.secoursEJB.entities.Client;
@Entity
public class Agence implements Serializable {

	private static final long serialVersionUID = 1L;

	private int codeAgence;
	@NotNull
	private String libelle_Agence;
	//relation client agence 
	private List<Client> clients;
	
	

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
	
	

	public String getLibelle_Agence() {
		return libelle_Agence;
	}

	public void setLibelle_Agence(String libelle_Agence) {
		this.libelle_Agence = libelle_Agence;
	}

	

	
   
}