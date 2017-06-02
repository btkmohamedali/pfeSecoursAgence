package com.iset.secoursEJB.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Versement")
public class Versement extends VersementRetrait{

	private static final long serialVersionUID = 1L;

	public Versement() 
	{

	}

}
