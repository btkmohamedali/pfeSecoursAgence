package com.iset.Connexion;


import java.sql.DriverManager;
import java.sql.SQLException;




public class ConnexionMYSQL {
		
	public  java.sql.Connection Connexion(String url) 
	 {   
		//System.out.println("-------- Mysql Connection Testing ------");  
		//url = "jdbc:mysql://localhost:3306/deltasecours_ag1?autoReconnect=true&useSSL=false";
		String user = "root";
		String password = "root";
		  try 
		    {
			     Class.forName("com.mysql.jdbc.Driver");
				 //System.out.println("Driver O.K.");

			} 
		   catch (ClassNotFoundException e) 
		    {
			   e.printStackTrace();
			}

			 // System.out.println("Mysql JDBC Driver Registered!");

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

