package com.iset.secoursEJB.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.iset.secoursEJB.entities.Compte;
/**
 * Entity implementation class for Entity: Operation
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Operation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer codeOperation;
	
	@Size(max=100)
	private String motif;
	
	@NotNull
	private Date date;
	@NotNull
	private Double montant;
	@NotNull
	private Compte compte;
	
	
	
	private Client client;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getCodeOperation() {
		return codeOperation;
	}
	public void setCodeOperation(Integer codeOperation) {
		this.codeOperation = codeOperation;
	}
	
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	
	@ManyToOne
	@JoinColumn(name="code_compteFK")
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	
	
	@ManyToOne
	@JoinColumn(name="code_clientFK")
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
	
	
	
	
	
	

}
