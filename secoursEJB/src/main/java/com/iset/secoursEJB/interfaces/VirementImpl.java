package com.iset.secoursEJB.interfaces;

import java.util.List;

import com.iset.secoursEJB.entities.Virement;

public interface VirementImpl {

	boolean insertVirement(Virement virement);
	List<Virement> findAll();
	int countNbrVirement();

}
