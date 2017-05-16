package com.iset.ClientSecours.managedbean;


import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.iset.secoursEJB.entities.Agence;
import com.iset.secoursEJB.interfaces.AgenceImpl;

@ManagedBean(name="agenceBean")
@SessionScoped
public class AgenceBean {



	@EJB
	private AgenceImpl daoAgence;
	
	private Agence agence=new Agence();
	
	

	

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public List<Agence> findAll()
	{
		return daoAgence.findAll();
	}
}
