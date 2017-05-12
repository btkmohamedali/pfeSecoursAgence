package com.iset.secoursEJB.interfaces;

import java.util.List;

import com.iset.secoursEJB.entities.RemiseCheque;

public interface RemiseChequeImpl {

	boolean insertRemiseCheque(RemiseCheque remiseCheque);
	List<RemiseCheque> findAll();

}
