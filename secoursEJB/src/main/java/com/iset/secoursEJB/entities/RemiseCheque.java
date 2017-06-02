package com.iset.secoursEJB.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class RemiseCheque extends AutreOperation {

	public RemiseCheque() {
	
		
	}
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private int numCheque;
	
	
	
	
	public int getNumCheque() {
		return numCheque;
	}
	public void setNumCheque(int numCheque) {
		this.numCheque = numCheque;
	}

	
}

