package com.iset.secoursEJB.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.iset.secoursEJB.entities.Operation;

@Entity
@DiscriminatorValue("RemiseCheque")
public class RemiseCheque extends Operation {

	public RemiseCheque() {
		super();
		
	}
	private static final long serialVersionUID = 1L;
	
	private int numCheque;
	@NotNull
	@Size(max=20)
	private	String rib;
	
	private int codeClt;
	
	private int codeCpte;
	
	public int getCodeCpte() {
		return codeCpte;
	}
	public void setCodeCpte(int codeCpte) {
		this.codeCpte = codeCpte;
	}
	
	
	public int getNumCheque() {
		return numCheque;
	}
	public void setNumCheque(int numCheque) {
		this.numCheque = numCheque;
	}
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
	
	
}

