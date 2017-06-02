package com.iset.secoursEJB.entities;

import javax.persistence.Entity;
import javax.validation.constraints.Size;


@Entity
public class RemiseEffet extends AutreOperation{

	private static final long serialVersionUID = 1L;
	
	private long numEffet;
	
	
	public long getNumEffet() {
		return numEffet;
	}
	public void setNumEffet(long numEffet) {
		this.numEffet = numEffet;
	}

	
	
	
	
}

