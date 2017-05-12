package com.iset.secoursEJB.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.iset.secoursEJB.entities.Compte;

@Remote
public interface CompteImpl {

	List<Compte> selectAll() ;
	List<Compte> findListCompteByCINClient(int cin);
	double findSoldeCompte(int code);
	
	Compte findByCode(int numCompte);
	double versement(int numCompte, double solde, double montant);
	double retrait(int numCompte, double solde, double montant);
	Compte updateCompte(Compte c);
}
