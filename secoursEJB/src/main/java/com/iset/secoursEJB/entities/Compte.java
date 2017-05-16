package com.iset.secoursEJB.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.iset.secoursEJB.entities.Client;
import com.iset.secoursEJB.entities.Operation;

@Entity
public class Compte implements Serializable{

	private static final long serialVersionUID = 1L;
	private long codeCompte;
	
	@NotNull()
	private double solde;
	@NotNull()
	private Date date_creation;
	@NotNull()
	private String type_compte;
	
	private double autorisation;
	
	
	//relation client compte
	private Client client;
			
	//relation compte operation
	private List<Operation> operations;
		
		
	
	//relation agence compte
	private Agence agence;
	

	
	
	public Compte() {
	
	}

	
	public String getType_compte() {
		return type_compte;
	}

	public void setType_compte(String type_compte) {
		this.type_compte = type_compte;
	}

	



	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	
	
	
	@Id
	@Column(name="codeCompte")
	public long getCodeCompte() {
		return codeCompte;
	}
	public void setCodeCompte(long codeCompte) {
		this.codeCompte = codeCompte;
	}


	public Date getDate_creation() {
		return date_creation;
	}


	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	@OneToMany(mappedBy="compte")
	public List<Operation> getOperations() {
		return operations;
	}


	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}


	@ManyToOne
	@JoinColumn(name="code_agenceFK")
	public Agence getAgence() {
		return agence;
	}
	public void setAgence(Agence agence) {
		this.agence = agence;
	}
	
	@ManyToOne
	@JoinColumn(name="code_clientFK")
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	

	public double getAutorisation() {
		return autorisation;
	}

	public void setAutorisation(double autorisation) {
		this.autorisation = autorisation;
	}
	
	
}
