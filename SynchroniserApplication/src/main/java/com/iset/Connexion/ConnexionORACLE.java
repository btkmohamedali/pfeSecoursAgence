package com.iset.Connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionORACLE {
	
	 public Connection Connexion() 
	 {
		 
		 
		//System.out.println("-------- Oracle JDBC Connection Testing ------");
		 String url = "jdbc:oracle:thin:@localhost:1521:xe";
	     String user = "delta";
	     String password = "delta";
	        try 
	        {

	            Class.forName("oracle.jdbc.driver.OracleDriver");

	        } 
	        catch (ClassNotFoundException e) 
	        {
	        	e.printStackTrace();
	        }

	        java.sql.Connection connection = null;

	        try 
	        {
	            connection = DriverManager.getConnection(url,user, password);
	        } 
	        catch (SQLException e) 
	        {
	        	e.printStackTrace();
	        }

			return connection;
	    }
}
