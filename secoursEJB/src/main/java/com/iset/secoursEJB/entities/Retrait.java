package com.iset.secoursEJB.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value = "Retrait")
public class Retrait extends VersementRetrait {

	public Retrait() 
	{

	}

	private static final long serialVersionUID = 1L;

	}

