package com.iset.Connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;




public class ConnexionORACLE {

	/*public Connection Connexion() throws NamingException, SQLException
	 {     
		
		Context ctx=new InitialContext();
	
		String url = "java:jboss/datasources/deltaDS";

       return ((DataSource)ctx.lookup(url)).getConnection();
		 
	 }*/
	 public Connection Connexion() 
	 {
		 
		 
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
