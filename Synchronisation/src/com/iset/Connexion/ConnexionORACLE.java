package com.iset.Connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class ConnexionORACLE {

	
	 public Connection Connexion() 
	 {
		 
		// Context ctx=new InitialContext();
         

          
       //  return ((DataSource)ctx.lookup("java:jboss/datasources/deltaDS")).getConnection();
		 
		 
		System.out.println("-------- Oracle JDBC Connection Testing ------");
		 String url = "jdbc:oracle:thin:@localhost:1521:xe";
	     String user = "delta";
	     String password = "delta";
	        try 
	        {

	            Class.forName("oracle.jdbc.driver.OracleDriver");

	        } 
	        catch (ClassNotFoundException e) 
	        {

	            System.out.println("Where is your Oracle JDBC Driver?");
	            e.printStackTrace();
	        }

	        System.out.println("Oracle JDBC Driver Registered!");

	        java.sql.Connection connection = null;

	        try 
	        {
	        	
	            connection = DriverManager.getConnection(url,user, password);
	            

	        } catch (SQLException e) {

	            System.out.println("Connection Failed! Check output console");
	            e.printStackTrace();
	           

	        }

	        if (connection != null) {
	            System.out.println("You made it, take control your database now!");
	        } else {
	            System.out.println("Failed to make connection!");
	        }
			return connection;
	    }
}
