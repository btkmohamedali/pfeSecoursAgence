package com.iset.secoursEJB.interfaces;

import java.util.List;

import com.iset.secoursEJB.entities.Versement;

public interface VersementImpl {

	boolean insertVersement(Versement Versement);
	List<Versement> findAll();
	long countNbrVersement();

}
