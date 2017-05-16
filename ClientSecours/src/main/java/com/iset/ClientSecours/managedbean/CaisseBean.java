package com.iset.ClientSecours.managedbean;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.iset.secoursEJB.entities.Caisse;
import com.iset.secoursEJB.interfaces.CaisseImpl;

@ManagedBean(name="caisseBean")
@SessionScoped
public class CaisseBean {

	@EJB
	private CaisseImpl daoCaisse;
	
	private Caisse Caisse=new Caisse();
	
	

	

	public Caisse getCaisse() {
		return Caisse;
	}

	public void setCaisse(Caisse Caisse) {
		this.Caisse = Caisse;
	}

	public List<Caisse> findAll()
	{
		return daoCaisse.findAllCaisse();
	}
}
