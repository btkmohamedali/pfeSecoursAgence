package com.iset.secoursEJB.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.iset.secoursEJB.entities.Caisse;

@Entity
public class Utilisateur implements Serializable {

	
	
	private static final long serialVersionUID = 1L;
	
	private String login;
	@NotNull
	private String password;
	@NotNull
	private String nomprenom;
	
	@NotNull
	private int cin;

	private String mail;
	
	

	//relation utilisateur caisse
	private List<Caisse> caisses;
	
	private List<Operation> operations;
	
	@Id
	@NotNull
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
		
	public int getCin() {
		return cin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	

	public String getNomprenom() {
		return nomprenom;
	}

	public void setNomprenom(String nomprenom) {
		this.nomprenom = nomprenom;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}

	@OneToMany(mappedBy="utilisateur")
	public List<Caisse> getCaisses() {
		return caisses;
	}

	public void setCaisses(List<Caisse> caisses) {
		this.caisses = caisses;
	}

	@OneToMany(mappedBy="utilisateur")
	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
		
	
	
	

	
}
