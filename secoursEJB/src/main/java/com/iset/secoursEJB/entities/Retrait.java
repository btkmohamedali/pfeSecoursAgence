package com.iset.secoursEJB.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.iset.secoursEJB.entities.Operation;

@Entity
@DiscriminatorValue("Retrait")
public class Retrait extends Operation {

	public Retrait() 
	{

	}

	private static final long serialVersionUID = 1L;

	}

