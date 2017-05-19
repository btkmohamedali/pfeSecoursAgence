package com.iset.Test;

import javax.naming.NamingException;

import com.iset.Methodes.Synchronisation;

public class Test {

	public static void main(String[] args) throws NamingException {
		Synchronisation oracle=new Synchronisation();
		//String url= "jdbc:mysql://localhost:3306/deltasecours?autoReconnect=true&useSSL=false";
		
		oracle.selectSynchronisation();
		
		
		//oracle.selectSynchronisation();
	}

}
