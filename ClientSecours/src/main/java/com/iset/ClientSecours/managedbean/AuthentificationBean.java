package com.iset.ClientSecours.managedbean;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
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

	public void logout() 
	{	
		System.out.println("logout");
		 System.exit(0);
	}
		
	public List<Utilisateur> findAll()
	{
		return daologin.findAllUsers();
	}
	public void sortir() throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().redirect("Authentification.xhtml");
	}
	
	public String findByLoginn()
	{
		System.out.println("hello fafi");
		System.out.println("login="+user.getLogin());
		 user=daologin.findByLogin(user.getLogin());
		 return user.getLogin();
	}

	public void RetourMenu() throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().redirect("Menu.xhtml");
	}
	public void envoie() throws IOException
	{
	    if (daologin.validateLogin(user.getLogin(),user.getPassword()))
	    {
	    		System.out.println("j'ai trouvé true");
			//ahawa kifech t3abi les variables de session mte3ek 
		    	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", user.getLogin());
			//bech temchi lel page "Menu.xhtml" mouch bel return "Menu.xhtml"
			FacesContext.getCurrentInstance().getExternalContext().redirect("Menu.xhtml");
			
		 }
	    else
	    {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "LOGIN", "LOGIN OR PASSWORD INVALID PLEASE TRY AGAIN!!");
			RequestContext.getCurrentInstance().update("growl");
			FacesContext context=FacesContext.getCurrentInstance();
			context.addMessage(null, message);
		}	
	} 
}
