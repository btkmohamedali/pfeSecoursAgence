package com.iset.secoursEJB.interfaces;

import java.util.List;

import com.iset.secoursEJB.entities.Caisse;

public interface CaisseImpl {

	Caisse updateCaisse(Caisse c);	

	List<Caisse> findAllCaisse();

	double retraitCaisse(int numcaisse,String loginuser,double solde, double montant);


	double versementCaisse(int numcaisse,String loginuser, double solde, double montant);

	public Caisse findCaisseByLoginAndNumC(String login, int codecaisse);

	Caisse findByCode(int numcaisse);

}
