package com.iset.secoursEJB.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Utilisateur implements Serializable {

	
	
	private static final long serialVersionUID = 1L;
	
	private String login;
	@NotNull
	private String password;
	@NotNull
	private String nomprenom;
	@NotNull
	private String puti;
	private String mail;
	
	private Agence agence ;
	
	private List<Caisse> caisses;
	private List<VersementRetrait> listeversementretraits;

	@Id
	@NotNull
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
		
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPuti() {
		return puti;
	}

	public void setPuti(String puti) {
		this.puti = puti;
	}

	

	public String getNomprenom() {
		return nomprenom;
	}

	public void setNomprenom(String nomprenom) {
		this.nomprenom = nomprenom;
	}

	
	

	
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@OneToOne()
	@JoinColumn(name="code_agenceFK")
	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	
		
	
	
	

	
}
