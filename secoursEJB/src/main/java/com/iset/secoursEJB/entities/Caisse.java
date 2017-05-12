package com.iset.secoursEJB.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Caisse
 *
 */
@Entity
public class Caisse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int codecaisse;
	@NotNull
	private double soldeCaisse ;
	@NotNull
	private Date datemodification;
	
	//relation caisse agence 
	private Agence agence;
	
	//relation caisse utilisateur
	private Utilisateur utilisateur;
	

	public Caisse() {
		
	}   
	   
	

	@Id    
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getCodecaisse() {
		return this.codecaisse;
	}

	public void setCodecaisse(int codecaisse) {
		this.codecaisse = codecaisse;
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
	@JoinColumn(name="code_utilisateurFK")
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}



	public double getSoldeCaisse() {
		return soldeCaisse;
	}



	public void setSoldeCaisse(double soldeCaisse) {
		this.soldeCaisse = soldeCaisse;
	}
	
	public Date getDatemodification() {
		return datemodification;
	}



	public void setDatemodification(Date datemodification) {
		this.datemodification = datemodification;
	}
   
}
