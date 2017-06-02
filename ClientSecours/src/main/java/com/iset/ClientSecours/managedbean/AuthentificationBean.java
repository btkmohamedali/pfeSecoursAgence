package com.iset.ClientSecours.managedbean;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

import com.iset.secoursEJB.entities.Agence;
import com.iset.secoursEJB.entities.Caisse;
import com.iset.secoursEJB.entities.Client;
import com.iset.secoursEJB.entities.Compte;
import com.iset.secoursEJB.entities.Utilisateur;
import com.iset.secoursEJB.interfaces.UtilisateurImpl;

@ManagedBean(name="authentificationBean")
@SessionScoped
public class AuthentificationBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UtilisateurImpl daologin;
	
	private Utilisateur user=new Utilisateur();
	private String login;
	private String password;
	private String code;
	private String puti;
	private int code_agence;
	private String nomprenom;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	
		
	public List<Utilisateur> findAll()
	{
		return daologin.findAllUsers();
	}
	
	public void sortir() throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getExternalContext().redirect("Authentification.xhtml");
	}
	
	public String findByLoginn()
	{
		System.out.println("hello fafi");
		System.out.println("login="+user.getLogin());
		 user=daologin.findByLogin(user.getLogin());
		 return user.getLogin();
	}
	

	public void validateSecret()
	{
		System.out.println(code);
		try 
		{
			if(code.equals("1234"))
			{	
			FacesContext.getCurrentInstance().getExternalContext().redirect("AffichageMotDePasse.xhtml");
			}
		
		else
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "SECRET INVALID essayez une autre fois !!");
			RequestContext.getCurrentInstance().update("growl");
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, message);
		}
	}
		catch (IOException e) {
			
				e.printStackTrace();
			}

		
    }
     
  
	public void RetourMenu() throws IOException
	{
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("Menu.xhtml");
	}
	
	public void  envoie() throws IOException
	{
	    if (daologin.validateLogin(user.getLogin(),user.getPassword()))
	    {
	    	System.out.println("j'ai trouvé true");
	    	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", user.getLogin());
	    	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("password", user.getPassword());
			//ahawa kifech t3abi les variables de session mte3ek 
	    	String log=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
	    	String pass=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("password");
	    	System.out.println("login="+log);
	    	System.out.println("password="+pass);
	    		 
		    //bech temchi lel page "Menu.xhtml" mouch bel return "Menu.xhtml"
			FacesContext.getCurrentInstance().getExternalContext().redirect("Menu.xhtml");
			
		}
	    else
	    {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "LOGIN", "LOGIN OR PASSWORD INVALID PLEASE TRY AGAIN!!");
			RequestContext.getCurrentInstance().update("growl");
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, message);
			System.out.println(user.getLogin()+user.getPassword());
			
			user=new Utilisateur();
			RequestContext.getCurrentInstance().reset("loginform");
		}
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPuti() {
		String log=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
		String pass=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("password");
		
		puti=daologin.findUser(log, pass).getPuti();
		System.out.println("getputi="+puti);
		return puti;
	}

	public void setPuti(String puti) {
		this.puti = puti;
	}
	public boolean chef;
	
	public boolean agent;
	
	public boolean isAgent() {
		if( getPuti().replace(" ", "").equals("AGC2") || getPuti().replace(" ", "").equals("AGC3"))
			return true;
		return agent;
	}

	public void setAgent(boolean agent) {
		this.agent = agent;
	}

	public boolean getChef() {
		return isChef();
	}

	public boolean isChef() {
		return getPuti().replace(" ", "").equals("AGC1");
	}

	public void setChef(boolean chef) {
		this.chef = chef;
	}

	public int getCode_agence() {
		return code_agence;
	}

	public void setCode_agence(int code_agence) {
		this.code_agence = code_agence;
	}

	
	/*private boolean selectedAgence = false;
	public boolean isSelectedAgence(){
		return code_agence!=0;
	}
	public void setSelectedAgence(boolean selectedAgence){
		this.selectedAgence = selectedAgence;
	}*/
	
	private List<Utilisateur> listUsers = new ArrayList<>();
	// kamel l getter and setter mta3 listUsers

	public void findUserByCodeAgence()
	{
		System.out.println("code agence :"+code_agence);
		setListUsers(daologin.findUserByAgence(code_agence));
	}

	public List<Utilisateur> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<Utilisateur> listUsers) {
		this.listUsers = listUsers;
	}

	
	public String getNomprenom() 
	{
		String log=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
		String pass=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("password");
		
		nomprenom=daologin.findUser(log, pass).getNomprenom();
		System.out.println("nom et prenom ="+nomprenom);
		return nomprenom;
	}

	public void setNomprenom(String nomprenom) {
		this.nomprenom = nomprenom;
	}

}
