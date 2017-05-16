package com.iset.ClientSecours.managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.iset.secoursEJB.entities.Caisse;
import com.iset.secoursEJB.interfaces.CaisseImpl;

@ManagedBean(name="caisseBean")
@SessionScoped
public class CaisseBean {

	@EJB
	private CaisseImpl daoCaisse;
	
	private Caisse Caisse=new Caisse();
	
	String loginFromSession = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
	
	private List<Caisse> listeCaisses=new ArrayList<Caisse>();

	public List<Caisse> getListeCaisses() {
		return listeCaisses;
	}

	public List<Caisse> setListeCaisses(List<Caisse> listeCaisses) {
		this.listeCaisses = listeCaisses;
		return listeCaisses;
	}

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

	public List<Caisse> afficheListeC()
	{
		return listeCaisses=daoCaisse.findListCaisseByLogin(loginFromSession);
	}
}
