package com.iset.Methodes;

import java.sql.SQLException;
import java.sql.Statement;

import com.iset.Connexion.ConnexionJDBC;

public class DeltaSecoursMYSQL {

	public void insertIntoDeltSecours()
	{
		try
		{
			System.out.println("afficher");
			String sqlexistance="INSERT INTO Utilisateur (`login`, `cin`, `mail`, `nomprenom`, `password`) "
					  + "VALUES ('hela',1500150,'hela@gmail.com','hzljjjj','fafi' );";
			ConnexionJDBC connexion=new ConnexionJDBC();
			/* Cr�ation de l'objet g�rant les requ�tes */
			Statement statement=connexion.Connexion().createStatement();
			/* Ex�cution d'une requ�te d'�criture */
			int statut = statement.executeUpdate(sqlexistance);
			/* Formatage pour affichage dans la JSP finale. */
			System.out.println("R�sultat de la requ�te d'insertion : " + statut + "." );
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	}
}
