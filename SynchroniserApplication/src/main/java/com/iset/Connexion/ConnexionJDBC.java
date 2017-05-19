package com.iset.Connexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class ConnexionJDBC {

	   
		public java.sql.Connection Connexion(String url) throws NamingException, SQLException
		 {     
			System.out.println("-------- Mysql Connection Testing ------");  
			//String url1 = "jdbc:mysql://localhost:3306/deltasecours?autoReconnect=true&useSSL=false";
			//String url2 = "jdbc:mysql://localhost:3306/deltasecours_ag2?autoReconnect=true&useSSL=false";
			
			Context ctx=new InitialContext();
		
			url = "java:jboss/datasources/deltaDS";

	       return ((DataSource)ctx.lookup(url)).getConnection();
			 
		}	
	/*public java.sql.Connection Connexion(String url) 
	 {   
		System.out.println("-------- Mysql Connection Testing ------");  
		//String url = "jdbc:mysql://localhost:3306/deltasecours?autoReconnect=true&useSSL=false";
		String user = "root";
		String password = "root";
		  try 
		      {
			     Class.forName("com.mysql.jdbc.Driver");
				 System.out.println("Driver O.K.");

			   } 
		   catch (ClassNotFoundException e) 
		      {

		    	  	System.out.println("Where is your Oracle JDBC Driver?");
			         e.printStackTrace();
			   }

			  System.out.println("Mysql JDBC Driver Registered!");

			  java.sql.Connection connection = null;

			try 
			{

			       connection = DriverManager.getConnection(url,user, password);
			} 
			        catch (SQLException e) {

			            System.out.println("Connection Failed! Check output console");
			            e.printStackTrace();
			            

			        }

			        if (connection != null) {
			            System.out.println("You made it, take control your database now!");
			        } else {
			            System.out.println("Failed to make connection!");
			        }
					return connection;
			    }*/
	
}

