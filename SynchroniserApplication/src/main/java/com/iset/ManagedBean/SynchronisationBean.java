package com.iset.ManagedBean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.iset.Connexion.ConnexionORACLE;
import com.iset.Methodes.SynchronisationDao;
import com.iset.entités.Synchronisation;
@ManagedBean(name="synchroBean")
@SessionScoped
public class SynchronisationBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	
	public List<Synchronisation> syn = new ArrayList<>();
	SynchronisationDao dao=new SynchronisationDao();
	ConnexionORACLE mysql=new ConnexionORACLE();
	
	@PostConstruct
	public void init(){
		dao.UpdateNonSynchro();
	}	
	public void synchroniser()
	{
		//dao.AjouterOperation("jdbc:mysql://localhost:3306/deltasecours_ag1");
		//dao.synchroniserClient("jdbc:mysql://localhost:3306/deltasecours_ag1");
		//dao.synchroniserCaisse("jdbc:mysql://localhost:3306/deltasecours_ag1");
		dao.synchronnisationBase();
		
	}
	
	
	
	public List<Synchronisation> select()
	{
		return dao.selectSynchronisation();
	}
	
	public List<Synchronisation> getSyn() {
		syn = dao.selectSynchronisation();
		return syn;
	}
	public void setSyn(List<Synchronisation> syn) {
		this.syn = syn;
	}
	
	public void affichertableSynchro()
	{
		 setSyn( syn);
	}
	public void cout() throws SQLException
	{
		String count="SELECT COUNT(*) AS count FROM synchronisation ";
		//String url = "jdbc:mysql://localhost:3306/deltasecours_ag1?autoReconnect=true&useSSL=false";
		Statement st=mysql.Connexion().createStatement();
		int countx=0;	
		System.out.println("********************insert synchronisation***************************");
			ResultSet rs3 = st.executeQuery(count);
		    while(rs3.next()){
		    countx = rs3.getInt("count");
		    }
			System.out.println(countx);
			//calculer la taille de la table de synchro
			
	}
}
