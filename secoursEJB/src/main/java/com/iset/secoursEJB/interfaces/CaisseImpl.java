package com.iset.secoursEJB.interfaces;

import java.util.List;

import com.iset.secoursEJB.entities.Caisse;

public interface CaisseImpl {

	Caisse updateCaisse(Caisse c);	

	List<Caisse> findAllCaisse();

	

//	public Caisse findCaisseByLoginAndNumC(String login, int codecaisse);

	Caisse findByCode(int numcaisse);

	List<Caisse> findListCaisseByLogin(String login);

	double versementCaisse(int numcaisse,String login, double solde, double montant);

	double retraitCaisse(int numcaisse, String login,double solde, double montant);

	Caisse findCaisseByLoginCode(String login, int code);

}
