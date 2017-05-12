package com.iset.secoursEJB.interfaces;

import java.util.List;

import com.iset.secoursEJB.entities.Retrait;

public interface RetraitImpl {

	boolean insertRetrait(Retrait Retrait);
	List<Retrait> findAll();


}
