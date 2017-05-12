package com.iset.secoursEJB.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.iset.secoursEJB.entities.Agence;

@Remote
public interface AgenceImpl {

	 List<Agence> findAll();
}
