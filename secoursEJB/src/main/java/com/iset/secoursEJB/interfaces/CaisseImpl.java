package com.iset.secoursEJB.interfaces;

import com.iset.secoursEJB.entities.Caisse;

public interface CaisseImpl {

	Caisse updateCaisse(Caisse c);

	double retraitCaisse(String login,double solde ,double montant);

	double versementCaisse(String login,double solde  , double montant);

	Caisse findByCode(int codecaisse);

	Caisse findCaisseByLogin(String loginFromSession);

}
