package com.iset.secoursEJB.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

import com.iset.secoursEJB.entities.Operation;

@Entity
@DiscriminatorValue("RemiseEffet")
public class RemiseEffet extends Operation{

	private static final long serialVersionUID = 1L;
	
	private long numEffet;
	private int codeClt;
	private int codeCpte;
	
	public int getCodeCpte() {
		return codeCpte;
	}
	public void setCodeCpte(int codeCpte) {
		this.codeCpte = codeCpte;
	}
	@Size(max=20)
	private	String rib;
	
	public long getNumEffet() {
		return numEffet;
	}
	public void setNumEffet(long numEffet) {
		this.numEffet = numEffet;
	}
	public int getCodeClt() {
		return codeClt;
	}
	public void setCodeClt(int codeClt) {
		this.codeClt = codeClt;
	}
	public String getRib() {
		return rib;
	}
	public void setRib(String rib) {
		this.rib = rib;
	}
	
	
	
	
}

