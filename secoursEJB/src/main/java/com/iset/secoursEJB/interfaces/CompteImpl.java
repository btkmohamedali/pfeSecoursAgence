package com.iset.secoursEJB.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.iset.secoursEJB.entities.Compte;

@Remote
public interface CompteImpl {

	List<Compte> selectAll() ;
	List<Compte> findListCompteByCINClient(String string);
	double findSoldeCompte(int code);
	
	Compte findByCode(long numCompte);
	double versement(long numCompte, double solde, double montant);
	double retrait(long l, double solde, double montant);
	Compte updateCompte(Compte c);
}
