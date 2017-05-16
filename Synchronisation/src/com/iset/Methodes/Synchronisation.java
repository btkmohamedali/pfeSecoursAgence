package com.iset.Methodes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.iset.Connexion.ConnexionJDBC;
import com.iset.Connexion.ConnexionORACLE;

public class Synchronisation {
	ConnexionORACLE oracle=new ConnexionORACLE();
	ConnexionJDBC mysql=new ConnexionJDBC();
	
	public void supprimerTables()
	{
		String nocheck="SET FOREIGN_KEY_CHECKS=0;";
		String deleteagence="DELETE FROM agence;";
		String check=" SET FOREIGN_KEY_CHECKS=1;";
		String dropclient="DELETE FROM client";
		String deleteevuti="DELETE FROM utilisateur";
		String dropoperation="DELETE FROM operation";
		String dropcaisse="DELETE FROM caisse";
		String dropcompte="DELETE FROM compte";
		//suppression de la table agence
	  Statement statement;
		try 
		{
			mysql.Connexion().setAutoCommit(false) ;
			statement = mysql.Connexion().createStatement();
			statement.addBatch(nocheck);
			
			statement.addBatch(deleteagence);
			statement.addBatch(deleteevuti);
			statement.addBatch(dropclient);
			statement.addBatch(dropoperation);
			statement.addBatch(dropcaisse);
			statement.addBatch(dropcompte);
			statement.addBatch(check);
			
			statement.executeBatch();
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
	    
	}

	public void synchroniserClient()
	{
		try
		{
			System.out.println("afficher client");
			String sqlexistance="select * from BKCLI";
			 
		    
		    Statement storacle=oracle.Connexion().createStatement();
		    ResultSet rsoracle=storacle.executeQuery(sqlexistance);
		    String requeteinsert="INSERT INTO"
					+ " client(codeClient,NID,TID,nom,prenom,code_agenceFK) "
					+ "VALUES (?,?,?,?,?,?)";
		    PreparedStatement stinsert = mysql.Connexion().prepareStatement(requeteinsert);
		    while(rsoracle.next())
		    {
				
				stinsert.setString(1, rsoracle.getString("CLI"));
		    	stinsert.setString(2, rsoracle.getString("NID"));
		    	stinsert.setString(3, rsoracle.getString("TID"));
		    	stinsert.setString(4, rsoracle.getString("NOM"));
		    	stinsert.setString(5, rsoracle.getString("PRE"));
		    	stinsert.setString(6, rsoracle.getString("AGE"));
		    	int rowsAdded =stinsert.executeUpdate();
		    	if (rowsAdded > 0) 
			    {
			        System.out.println(" added successfully client!");
			    }
		    }
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
		
	}
	
	public void nocheck() 
	{
		try 
		{
			System.out.println("SET foreign_key_checks = 0");
			String contraintes="ALTER TABLE deltasecours.caisse DROP FOREIGN KEY code_agenceFK";
			//suppression de la table caisse
			
		    Statement stcont= mysql.Connexion().createStatement();
		    int row=stcont.executeUpdate(contraintes);
		    System.out.println(row);
		    if (row != 0) 
		    {
		        System.out.println(" contrainte desactivée!");
		    }
	    } 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
	}
	
	public void check() 
	{
		try 
		{
			System.out.println("SET foreign_key_checks = 1;");
			String contraintes="SET foreign_key_checks = 1;";
			//suppression de la table caisse
			
		    PreparedStatement stcont= mysql.Connexion().prepareStatement(contraintes);
		    int row=stcont.executeUpdate();
		    System.out.println(row);
		    if (row > 0) 
		    {
		        System.out.println(" contrainte desactivée!");
		    }
	    } catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public void synchroniserAgence()
	{
		try
		{
			String sqlexistance="select * from BKAGE";
			/* Création de l'objet gérant les requêtes */
			Statement storacle=oracle.Connexion().createStatement();
			
			/* Exécution d'une requête de lecture */
		    ResultSet rscount=storacle.executeQuery(sqlexistance);
		    String requeteinsert="INSERT INTO agence (codeAgence,libelle_Agence) VALUES (?,?)";
    		PreparedStatement stinsert = mysql.Connexion().prepareStatement(requeteinsert);
	    	
	    	while(rscount.next())
		    {
	    		stinsert.setString(1, rscount.getString("AGE"));
		    	stinsert.setString(2, rscount.getString("LIB"));
		    	int rowsAdded =stinsert.executeUpdate();
		    	if(rowsAdded>0)
		    	System.out.println("agence added with succes");
		    }
		    
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	}

	public void synchroniserEvuti()
	{
		try
		{
			System.out.println("afficher utilisateur");
			String sqlexistance="select * from EVUTI";
			String requeteinsert="INSERT INTO utilisateur (login,cin,mail,nomprenom,password) VALUES (?,?,?,?,?)";
			
			Statement storacle=oracle.Connexion().createStatement();
		    ResultSet rsselect=storacle.executeQuery(sqlexistance);
		    PreparedStatement stinsert = mysql.Connexion().prepareStatement(requeteinsert);
		    while(rsselect.next())
		    {
		    	stinsert.setString(1, rsselect.getString("CUTI"));
		    	stinsert.setInt(2, 000000);
		    	stinsert.setString(3, rsselect.getString("EMAIL"));
			  
		    	stinsert.setString(4, rsselect.getString("LIB"));
		    	
		    	stinsert.setString(5, rsselect.getString("MDP"));
		    	int rowsAdded =stinsert.executeUpdate();
		    	if (rowsAdded > 0) 
			    {
		    		
			        System.out.println(" added successfully utilisateur!");
			    }
		    	/*System.out.println("cuti="+rsselect.getString("CUTI"));
		    	System.out.println("mot de passe="+rsselect.getString("MDP"));
		    	System.out.println("lib="+rsselect.getString("LIB"));*/
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
		
	public void synchroniserCaisse()
	{
		try
		{
			
			String sqlexistance="select * from BKCAI";
			String requeteinsert="INSERT INTO caisse(numerocaisse,soldeCaisse,code_agenceFK,code_utilisateurFK) VALUES (?,?,?,?)";
			 
		    PreparedStatement stinsert = mysql.Connexion().prepareStatement(requeteinsert);
		    Statement storacle=oracle.Connexion().createStatement();
		    ResultSet rscaisse=storacle.executeQuery(sqlexistance);
		    while(rscaisse.next())
		    {
		    	
		    	
		    	stinsert.setString(1, rscaisse.getString("CAI"));
		    	
		    	stinsert.setDouble(2, rscaisse.getDouble("SJO"));
		    	
		    	stinsert.setString(3, rscaisse.getString("AGE"));
			  
		    	stinsert.setString(4, rscaisse.getString("CUTI"));
		    	
		    	int rowsAdded =stinsert.executeUpdate();
		    	if (rowsAdded > 0) 
			    {
		    		System.out.println(rowsAdded);
			        System.out.println(" added successfully caisse!");
			    }
		    	/*System.out.println("\n agence= "+rscaisse.getString("AGE"));
		    	System.out.println("code caisse= "+rscaisse.getString("CAI"));
		    	
		    	System.out.println("date= "+rscaisse.getString("DOU"));
		    	System.out.println("solde= "+rscaisse.getString("SJO"));
		    	System.out.println("code utilisateur= "+rscaisse.getString("CUTI"));*/
		    }
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	}
	
	public void synchroniserCompte()
	{
		try
		{
			System.out.println("afficher compte");
			String sqlexistance="select * from BKCOM";
			
			String requeteinsert="INSERT INTO compte(codeCompte,autorisation,date_creation,solde,type_compte,code_agenceFK,code_clientFK) VALUES (?,?,?,?,?,?,?)";
			
		    PreparedStatement stinsert = mysql.Connexion().prepareStatement(requeteinsert);
		    Statement storacle=oracle.Connexion().createStatement();
		    ResultSet rscompte=storacle.executeQuery(sqlexistance);
		    /* Exécution d'une requête de lecture */
			System.out.println(rscompte.next());
		    while((rscompte.next()))
		    {
		    	
		    	stinsert.setString(1, rscompte.getString("NCP"));
		    	
		    	stinsert.setDouble(2,0);
		    	
		    	stinsert.setDate(3, rscompte.getDate("DOU"));
			  
		    	stinsert.setDouble(4, rscompte.getDouble("SIN"));
		    	
		    	stinsert.setString(5, rscompte.getString("INTI"));
		    	
		    	stinsert.setInt(6, rscompte.getInt("AGE"));
		    	
		    	stinsert.setInt(7, rscompte.getInt("CLI"));
			  
		    	int rowsAdded =stinsert.executeUpdate();
		    	
		    	if (rowsAdded > 0) 
				    {
			    		System.out.println(rowsAdded);
				        System.out.println(" added successfully compte!");
				    }
		    }	
		
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	}
	
	public void deleteDatabase()
	{
		String drop="drop database deltasecours";
		//suppression de la table agence
	    PreparedStatement statement ;
		try 
		{
		
		statement = mysql.Connexion().prepareStatement(drop);
		int delete=statement.executeUpdate();
		if(delete > 0)
		{
			System.out.println("database deleted");
		}
		
		 
		
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
	}
	
	public void createDatabase()
	{
		String drop="create database deltasecours";
		//suppression de la table agence
		PreparedStatement statement ;
		try 
		{
			
			statement = mysql.Connexion().prepareStatement(drop);
			int delete=statement.executeUpdate();
			if(delete > 0)
			{
				System.out.println("database created");
			}
			
			 
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	

	public void updateAutorisation()
	{
		try 
		{
			String sqlexistance="select BKAUTC.MAUT, BKAUTC.NCP "
					+ "from BKAUTC "
					+ "where trunc (sysdate)<= BKAUTC.ech "
					
					+ "and BKAUTC.maut>0 "
					+ "and BKAUTC.sit = 'O' "
					+ " and (age , NCP) in "
					+ "(select age ,NCP from BKCOM where cfe='N')";;
			Statement storacle=oracle.Connexion().createStatement();
			ResultSet rsauto=storacle.executeQuery(sqlexistance);
			
			Statement stupdate = mysql.Connexion().createStatement();  
			
			while (rsauto.next()) 
			{
				System.out.println(rsauto.getString("NCP"));
				System.out.println(rsauto.getString("MAUT"));
				String updateauto="UPDATE compte set autorisation="
				+rsauto.getDouble("MAUT")+"where codeCompte="+rsauto.getString("NCP");
		
				int x=stupdate.executeUpdate(updateauto);
				System.out.println(x);
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
}
