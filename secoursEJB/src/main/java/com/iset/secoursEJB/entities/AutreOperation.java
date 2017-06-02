package com.iset.secoursEJB.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
@SuppressWarnings("serial")
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class AutreOperation extends Operation {

	@Size(max=20)
	private	String rib;
	
	private int codeClt;
	
	private long codeCpte;
	private Utilisateur utilisateur;

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public int getCodeClt() {
		return codeClt;
	}

	public void setCodeClt(int codeClt) {
		this.codeClt = codeClt;
	}

	public long getCodeCpte() {
		return codeCpte;
	}

	public void setCodeCpte(long numB) {
		this.codeCpte = numB;
	}
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="code_utilisateurFK")
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	
	
}
