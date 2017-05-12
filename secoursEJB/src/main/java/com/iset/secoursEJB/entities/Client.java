package com.iset.secoursEJB.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.iset.secoursEJB.entities.Agence;
import com.iset.secoursEJB.entities.Compte;


@Entity
public class Client implements Serializable{

private static final long serialVersionUID = 1L;

	
	private int codeClient;
	
	@NotNull
	private int cin;
	@NotNull
	private String nom;
	@NotNull
	private String prenom;
	
	
	private List<Compte> comptes;
	
	private List<Operation> operations;
	
	private Agence agence;
	
	public Client() {
	
	}
	@Id
	@NotNull
	public int getCodeClient() {
		return codeClient;
	}
	public void setCodeClient(int codeClient) {
		this.codeClient = codeClient;
	}
	
	public int getCin() {
		return cin;
	}
	
	public void setCin(int cin) {
		this.cin = cin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	@OneToMany(mappedBy="client")
	public List<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	@ManyToOne
	@JoinColumn(name="code_agenceFK")
	public Agence getAgence() {
		return agence;
	}
	public void setAgence(Agence agence) {
		this.agence = agence;
	}
	
	@OneToMany(mappedBy="client")
	public List<Operation> getOperations() {
		return operations;
	}
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
	
	

}
