package com.iset.secoursEJB.interfaces;

import java.util.List;

import com.iset.secoursEJB.entities.RemiseEffet;

public interface RemiseEffetImpl {



	boolean insertRemiseEffet(RemiseEffet remiseEffet);
	List<RemiseEffet> findAll();
	long countNbrRemiseE();

}
