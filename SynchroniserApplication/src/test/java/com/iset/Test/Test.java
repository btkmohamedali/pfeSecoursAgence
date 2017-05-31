package com.iset.Test;

import com.iset.Connexion.ConnexionORACLE;
import com.iset.Methodes.SynchronisationDao;

public class Test {

	public static void main(String[] args)  {
		SynchronisationDao dao=new SynchronisationDao();
		//String url= "jdbc:mysql://localhost:3306/deltasecours?autoReconnect=true&useSSL=false";
		
		//oracle.selectSynchronisation();
		//dao.synchronnisationBase();
		
		//oracle.selectSynchronisation();
			//dao.synchronnisationBase();
			
		ConnexionORACLE oracle=new ConnexionORACLE();
		oracle.Connexion();
	}

}
