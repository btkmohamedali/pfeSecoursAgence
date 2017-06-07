package com.iset.Methodes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.iset.entités.*;



import com.iset.Connexion.ConnexionMYSQL;
import com.iset.Connexion.ConnexionORACLE;

public class SynchronisationDao {
	ConnexionORACLE oracle=new ConnexionORACLE();
	ConnexionMYSQL mysql=new ConnexionMYSQL();
	
	
	
	
	public void supprimerOperation(String url)
	{
		String nocheck="SET FOREIGN_KEY_CHECKS=0;";
		String check=" SET FOREIGN_KEY_CHECKS=1;";
		String dropoperation="DELETE FROM operation";
		String dropverretrait="DELETE FROM versementretrait";
		String autreop="DELETE FROM autreoperation";
		String versement="DELETE FROM versement";
		String retrait="DELETE FROM retrait";
		String remiseC="DELETE FROM remisecheque";
		String remiseE="DELETE FROM remiseeffet";
		String virement="DELETE FROM virement";
		String caisse="DELETE FROM caisse";
		//suppression de la table agence
	  
		try 
		{
			mysql.Connexion(url).setAutoCommit(false) ;
			
			Statement statement = mysql.Connexion(url).createStatement();
			statement.addBatch(nocheck);
			
			statement.addBatch(dropoperation);
			statement.addBatch(autreop);
			statement.addBatch(dropverretrait);
			statement.addBatch(versement);
			statement.addBatch(retrait);
			statement.addBatch(remiseC);
			statement.addBatch(remiseE);
			statement.addBatch(virement);
			statement.addBatch(caisse);
			statement.addBatch(check);
			
			statement.executeBatch();
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
	}
	public void supprimerTables(String url)  
	{
		String nocheck="SET FOREIGN_KEY_CHECKS=0;";
		String check=" SET FOREIGN_KEY_CHECKS=1;";
		String dropclient="DELETE FROM client";
		String deleteevuti="DELETE FROM utilisateur";
		String dropagence="DELETE FROM agence;";
		String dropcompte="DELETE FROM compte";
		//suppression de la table agence
	  
		try 
		{
			mysql.Connexion(url).setAutoCommit(false) ;
			
			Statement statement = mysql.Connexion(url).createStatement();
			statement.addBatch(nocheck);
			statement.addBatch(dropagence);
			statement.addBatch(deleteevuti);
			statement.addBatch(dropclient);
			statement.addBatch(dropcompte);
			
			statement.addBatch(check);
			
			statement.executeBatch();
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
	    
	}

	public void synchroniserClient(String url)  
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
		    PreparedStatement stinsert = mysql.Connexion(url).prepareStatement(requeteinsert);
		    while(rsoracle.next())
		    {
				
				stinsert.setString(1, rsoracle.getString("CLI"));
		    	stinsert.setString(2, rsoracle.getString("NID"));
		    	stinsert.setString(3, rsoracle.getString("TID"));
		    	stinsert.setString(4, rsoracle.getString("NOM"));
		    	stinsert.setString(5, rsoracle.getString("PRE"));
		    	stinsert.setString(6, rsoracle.getString("AGE"));
		    	stinsert.executeUpdate();
		    	
		    }
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
		
	}
	
	public void synchroniserAgence(String url)  
	{
		try
		{
			System.out.println("afficher agence");
			String sqlexistance="select * from BKAGE";
			/* Création de l'objet gérant les requêtes */
			Statement storacle=oracle.Connexion().createStatement();
			
			/* Exécution d'une requête de lecture */
		    ResultSet rscount=storacle.executeQuery(sqlexistance);
		    String requeteinsert="INSERT INTO agence (codeAgence,libelle_Agence) VALUES (?,?)";
    		PreparedStatement stinsert = mysql.Connexion(url).prepareStatement(requeteinsert);
	    	
	    	while(rscount.next())
		    {
	    		stinsert.setString(1, rscount.getString("AGE"));
		    	stinsert.setString(2, rscount.getString("LIB"));
		    	stinsert.executeUpdate();
		    	
		    }
		    
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	}

	public void synchroniserEvuti(String url) 
	{
		try
		{
			System.out.println("afficher utilisateur");
			String sqlexistance="select * from EVUTI";
			String requeteinsert="INSERT INTO utilisateur (login,mail,nomprenom,password,puti,code_agenceFK) VALUES (?,?,?,?,?,?)";
			
			Statement storacle=oracle.Connexion().createStatement();
		    ResultSet rsselect=storacle.executeQuery(sqlexistance);
		    PreparedStatement stinsert = mysql.Connexion(url).prepareStatement(requeteinsert);
		    while(rsselect.next())
		    {
		    	stinsert.setString(1, rsselect.getString("CUTI"));
		    	
		    	stinsert.setString(2, rsselect.getString("EMAIL"));
			  
		    	stinsert.setString(3, rsselect.getString("LIB"));
		    	
		    	stinsert.setString(4, rsselect.getString("MDP"));
		    	
		    	stinsert.setString(5, rsselect.getString("PUTI"));

		    	stinsert.setString(6, rsselect.getString("AGE"));
		    	stinsert.executeUpdate();
		    	
		    }
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	}
		
	public void synchroniserCaisse(String url)  
	{
		try
		{
			System.out.println("afficher caisse");
			String sqlexistance="select * from BKCAI";
			String requeteinsert="INSERT INTO caisse(numerocaisse,soldeCaisse,code_agenceFK,code_operationFK,code_utilisateurFK) VALUES (?,?,?,?,?)";
			 
		    PreparedStatement stinsert = mysql.Connexion(url).prepareStatement(requeteinsert);
		    Statement storacle=oracle.Connexion().createStatement();
		    ResultSet rscaisse=storacle.executeQuery(sqlexistance);
		 
			
			
		    while(rscaisse.next())
		    {
				
				stinsert.setString(1, rscaisse.getString("CAI"));
		    	
		    	stinsert.setDouble(2, rscaisse.getDouble("SJO"));
		    	
		    	stinsert.setString(3, rscaisse.getString("AGE"));
		    	
		    	stinsert.setString(4, null);
			  
		    	stinsert.setString(5, rscaisse.getString("CUTI"));
		    	
		    	stinsert.executeUpdate();
		    	
		    	/*System.out.println("\n agence= "+rscaisse.getString("AGE"));
		    	System.out.println("code caisse= "+rscaisse.getString("CAI"));
		    	
		    	System.out.println("date= "+rscaisse.getString("DOU"));
		    	System.out.println("solde= "+rscaisse.getString("SJO"));
		    	System.out.println("code utilisateur= "+rscaisse.getString("CUTI"));*/
		    	//statement.addBatch(nocheck);
		    }
		  
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	}
	
	public void synchroniserCompte(String url) 
	{
		try
		{
			System.out.println("afficher compte");
			String sqlexistance="select * from BKCOM";
			
			String requeteinsert="INSERT INTO compte(codeCompte,autorisation,date_creation,solde,type_compte,code_agenceFK,code_clientFK) VALUES (?,?,?,?,?,?,?)";
			
		    PreparedStatement stinsert = mysql.Connexion(url).prepareStatement(requeteinsert);
		    Statement storacle=oracle.Connexion().createStatement();
		    ResultSet rscompte=storacle.executeQuery(sqlexistance);
		    /* Exécution d'une requête de lecture */
			
		    while((rscompte.next()))
		    {
		    	
		    	stinsert.setString(1, rscompte.getString("NCP"));
		    	
		    	stinsert.setDouble(2,0);
		    	
		    	stinsert.setDate(3, rscompte.getDate("DOU"));
			  
		    	stinsert.setDouble(4, rscompte.getDouble("SIN"));
		    	
		    	stinsert.setString(5, rscompte.getString("INTI"));
		    	
		    	stinsert.setInt(6, rscompte.getInt("AGE"));
		    	
		    	stinsert.setInt(7, rscompte.getInt("CLI"));
			  
		    	stinsert.executeUpdate();
		    	
		    }	
		
		}
	    catch(SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	}
	
	public void updateAutorisation(String url)  
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
			
			Statement stupdate = mysql.Connexion(url).createStatement();  
			
			while (rsauto.next()) 
			{
				
				String updateauto="UPDATE compte set autorisation="
				+rsauto.getDouble("MAUT")+"where codeCompte="+rsauto.getString("NCP");
		
				stupdate.executeUpdate(updateauto);
				
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			
		}
		
	}

	
	public void UpdateNonSynchro()
	{
		String update="UPDATE SYNCHRONISATION set ETAT='base non synchronisé'";
		System.out.println(update);
		Statement stupdate;
		try {
			stupdate = oracle.Connexion().createStatement();
			int rows=stupdate.executeUpdate(update);
		  	System.out.println(rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void synchronnisationBase ()
	{
		//String count="SELECT COUNT(*) AS count FROM synchronisation ";
		String select="SELECT * FROM SYNCHRONISATION";
		String x="";
		
		try 
		{
			
			Statement st=oracle.Connexion().createStatement();
			
			ResultSet rs=st.executeQuery(select);
			while(rs.next())
			{
				//rs.next();
				
				//System.out.println(rs.next());
				String lib=rs.getString("LIBELLE_AGENCE");
				String datasource_agence=rs.getString("DATASOURCE_AG");
				System.out.println("agence="+lib);
				System.out.println("datasource recuperée="+datasource_agence);
				
						supprimerTables(datasource_agence);
						supprimerOperation(datasource_agence);
						synchroniserAgence(datasource_agence);
						synchroniserEvuti(datasource_agence);
						synchroniserClient(datasource_agence);
						synchroniserCompte(datasource_agence);
						updateAutorisation(datasource_agence);
						synchroniserCaisse(datasource_agence);
						x="synchronisation effectué avec succes";
						//System.out.println(x);
					
					
			String update="UPDATE SYNCHRONISATION set ETAT='"+x+"' where LIBELLE_AGENCE='"+lib+"'";
			System.out.println(update);
			Statement stupdate=oracle.Connexion().createStatement();
			int rows=stupdate.executeUpdate(update);
			  	System.out.println(rows);
			}
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		} 
		
	}

	
	
	public List<Synchronisation> selectSynchronisation () 
	{
		ArrayList<com.iset.entités.Synchronisation> listfam=new ArrayList<>();
		//String count="SELECT COUNT(*) FROM SYNCHRONISATION";
		String select="SELECT * FROM SYNCHRONISATION";
		try 
		{
			Statement stselect=oracle.Connexion().createStatement();
			ResultSet rs=stselect.executeQuery(select);
			//System.out.println("before while");
			while (rs.next())
			{
				com.iset.entités.Synchronisation s = new com.iset.entités.Synchronisation();
				s.setDATASOURCE_AG(rs.getString("DATASOURCE_AG"));
				s.setETAT(rs.getString("ETAT"));
				s.setLIBELLE_AGENCE(rs.getString("LIBELLE_AGENCE"));
				listfam.add(s);
				//System.out.println("after while");
				//System.out.println("LIBELLE_AGENCE="+rs.getString("LIBELLE_AGENCE"));
				//System.out.println("DATASOURCE_AG="+rs.getString("DATASOURCE_AG"));
				//System.out.println("ETAT="+rs.getString("ETAT"));
				
			}
			
			return listfam;
			
		} catch (SQLException e) {
			
			e.getMessage();
			e.printStackTrace();
			return null;
		} 
		
	}
	
	
}
