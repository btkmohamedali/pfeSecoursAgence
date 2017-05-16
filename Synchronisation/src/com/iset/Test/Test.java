package com.iset.Test;

import com.iset.Methodes.Synchronisation;

public class Test {

	public static void main(String[] args) {
		Synchronisation oracle=new Synchronisation();
			
		oracle.supprimerTables();
		oracle.synchroniserAgence();
		oracle.synchroniserEvuti();
		oracle.synchroniserCaisse();
		oracle.synchroniserClient();
		oracle.synchroniserCompte();
		oracle.updateAutorisation();
	}

}
