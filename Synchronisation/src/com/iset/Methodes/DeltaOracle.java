package com.iset.Methodes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.iset.Connexion.ConnexionJDBC;
import com.iset.Connexion.ConnexionORACLE;

public class DeltaOracle {
	ConnexionORACLE oracle=new ConnexionORACLE();
	ConnexionJDBC mysql=new ConnexionJDBC();
	
	public void synchroniserAgence()
	{
		try
		{
			
			String sqlexistance="select * from BKAGE";
			String requetedelete="DELETE FROM agence";
			String requeteinsert="INSERT INTO agence (codeAgence,libelle_Agence) VALUES (?, ?)";
			//suppression de la table agence
		    PreparedStatement statement = mysql.Connexion().prepareStatement(requetedelete);
		    int rowsDeleted = statement.executeUpdate();
		    if (rowsDeleted > 0) 
		    {
		        System.out.println(" deleted successfully!");
		    }
			/* Création de l'objet gérant les requêtes */
			Statement storacle=oracle.Connexion().createStatement();
			/* Exécution d'une requête de lecture */
		    ResultSet rscount=storacle.executeQuery(sqlexistance);
		    while(rscount.next())
		    {
		    	PreparedStatement stinsert = mysql.Connexion().prepareStatement(requeteinsert);
		    	
		    	stinsert.setString(1, rscount.getString("AGE"));
		    	stinsert.setString(2, rscount.getString("LIB"));
		    	int rowsAdded =stinsert.executeUpdate();
		    	if (rowsAdded > 0) 
			    {
			        System.out.println(" added successfully!");
			    }
		    	//System.out.println(rscount.getString("AGE"));
		    	//System.out.println(rscount.getString("LIB"));
		    	
		    }
		    
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	}

	public void synchroniserCaisse()
	{
		try
		{
			
			String sqlexistance="select * from BKCAI";
			String requetedelete="DELETE FROM caisse";
			String requeteinsert="INSERT INTO caisse (codecaisse,soldeCaisse,code_agenceFK,code_utilisateurFK) VALUES (?,?,?,?)";
			//suppression de la table agence
		    PreparedStatement statementcaisse = mysql.Connexion().prepareStatement(requetedelete);
		    int rowsDeleted = statementcaisse.executeUpdate();
		    if (rowsDeleted > 0) 
		    {
		        System.out.println(" deleted successfully!");
		    } 
		    String sqlagence="select * from BKAGE";
		    ResultSet rsagence= oracle.Connexion().createStatement().executeQuery(sqlagence);
		    Statement storacle=oracle.Connexion().createStatement();
		    ResultSet rscount=storacle.executeQuery(sqlexistance);
		    while(rscount.next())
		    {
		    	PreparedStatement stinsert = mysql.Connexion().prepareStatement(requeteinsert);
		    	
		    	stinsert.setString(1, rscount.getString("CAI"));
		    	
		    	stinsert.setString(2, rscount.getString("SJO"));
		    	while(rsagence.next())
			    {
		    	stinsert.setString(3, rsagence.getString("AGE"));
			    }
		    	stinsert.setString(4, rscount.getString("CUTI"));
		    	int rowsAdded =stinsert.executeUpdate();
		    	if (rowsAdded > 0) 
			    {
			        System.out.println(" added successfully!");
			    }
		    	/*System.out.println("\n agence= "+rscount.getString("AGE"));
		    	System.out.println("code caisse= "+rscount.getString("CAI"));
		    	System.out.println("code utilisateur= "+rscount.getString("CUTI"));
		    	System.out.println("date= "+rscount.getString("DOU"));
		    	System.out.println("solde= "+rscount.getString("SJO"));*/
		    }
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	}
	
	public void afficherEvuti()
	{
		try
		{
			System.out.println("afficher caisse");
			String sqlexistance="select * from EVUTI";
			
		    
		    Statement storacle=oracle.Connexion().createStatement();
		    ResultSet rscount=storacle.executeQuery(sqlexistance);
		    while(rscount.next())
		    {
		    	System.out.println("cuti="+rscount.getString("CUTI"));
		    	System.out.println("mot de passe="+rscount.getString("MDP"));
		    	System.out.println("lib="+rscount.getString("LIB"));
		    	//cuti=rscount.getString("CUTI");
		    	//mdp=rscount.getString("MDP");
		    	//lib=rscount.getString("LIB");
		    }
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	}

	public void afficherCompte()
	{
		try
		{
			System.out.println("afficher compte");
			String sqlexistance="select * from BKCOM";
			
		    
		    Statement storacle=oracle.Connexion().createStatement();
		    ResultSet rscount=storacle.executeQuery(sqlexistance);
		    while(rscount.next())
		    {
		    	System.out.println("numero compte="+rscount.getString("NCP"));
		    	System.out.println("agence ="+rscount.getString("AGE"));
		    	System.out.println("code client="+rscount.getString("CLI"));
		    	System.out.println("type_compte="+rscount.getString("INTI"));
		    	System.out.println("date douverture="+rscount.getString("DOU"));
		    	System.out.println("solde="+rscount.getString("SIN"));
		    	System.out.println("autorisation="+rscount.getString(""));
		    }
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	}
	
	public void afficherClient()
	{
		try
		{
			System.out.println("afficher client");
			String sqlexistance="select * from BKCLI";
			
		    
		    Statement storacle=oracle.Connexion().createStatement();
		    
		    ResultSet rscount=storacle.executeQuery(sqlexistance);
		    while(rscount.next())
		    {
		    	System.out.println("code client="+rscount.getString("CLI"));
		    	System.out.println("nom client="+rscount.getString("NOM"));
		    	System.out.println("prenom client="+rscount.getString("PRE"));
		    	System.out.println("code agence="+rscount.getString("AGE"));
		    	System.out.println("type identifiant="+rscount.getString("TID"));
		    	System.out.println("code identifiant="+rscount.getString("NID"));
		    }
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
		
	}
}
