package com.iset.secoursEJB.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.iset.secoursEJB.entities.Operation;

@Entity
@DiscriminatorValue("Versement")
public class Versement extends Operation{

	private static final long serialVersionUID = 1L;

	public Versement() 
	{

	}

}
