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
			/* Création de l'objet gérant les requêtes */
			Statement statement=connexion.Connexion().createStatement();
			/* Exécution d'une requête d'écriture */
			int statut = statement.executeUpdate(sqlexistance);
			/* Formatage pour affichage dans la JSP finale. */
			System.out.println("Résultat de la requête d'insertion : " + statut + "." );
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	}
}
