package com.iset.secoursEJB.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iset.secoursEJB.interfaces.UtilisateurImpl;
import com.iset.secoursEJB.entities.Utilisateur;


@SuppressWarnings("serial")
@Stateless
public class UtilisateurDAO implements UtilisateurImpl,Serializable{

	
	@PersistenceContext(unitName="secoursEJB")
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public boolean validateLogin(String log, String pass) 
	{
		try {
		  //getSingleResult() 
		  //Execute a SELECT query that returns a single untyped result.
		Utilisateur login= (Utilisateur) getEntityManager().createQuery("SELECT l FROM Utilisateur l WHERE l.login ='"+log+"' AND l.password ='"+ pass+"'").getSingleResult();
		if (login != null)
		{
			System.out.println("found");
			
			return true;
		}		       
		} 
	catch (NoResultException e)
		{
		System.out.println("pas de resultat");
		return false;
		}
		return false;
	}

	@Override
	public Utilisateur findByLogin(String username) {
		
		return getEntityManager().find(Utilisateur.class, username);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> findAllUsers() 
	{	 
		Query query =getEntityManager().createQuery(" SELECT u FROM Utilisateur u ");
		return query.getResultList();
	}
	
	@Override
	public List<String> findAllEmail(){
		List<Utilisateur> users = findAllUsers();
		
		ArrayList<String> listEmail = new ArrayList<>();
		
		for(Utilisateur user : users)
			listEmail.add(user.getMail());
		return listEmail;
	}
	
	@Override
	public List<String> findAllMDPUsers(){
		List<Utilisateur> users = findAllUsers();
		
		ArrayList<String> listMDP = new ArrayList<>();
		
		for(Utilisateur user : users)
			listMDP.add("nom prenom = "+user.getNomprenom()+" mot de passe = "+user.getPassword()+"\n");
		
		return listMDP;
	}
	
	@Override
	public void Mail(String email,List<String> MDPUsers){
		
		String smtpHost = "smtp.gmail.com";
	    String from = "arfaouighaith10@gmail.com";
	    //String to = "fadhlounferiel@gmail.com";
	    String username = "arfaouighaith10@gmail.com";
	    String password = "10111995";
	 
	    Properties props = new Properties();
	    props.put("mail.smtp.host", smtpHost);
	    props.put("mail.smtp.ssl.trust", smtpHost);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	 
	    String messageaenvoyer=MDPUsers.toString();
	    Session session = Session.getDefaultInstance(props);
	    session.setDebug(true);
	 
	    MimeMessage message = new MimeMessage(session);   
	    try 
	    {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		    message.setSubject("Changement de mot de passe");
		    message.setText(messageaenvoyer);
		 
		    Transport tr = session.getTransport("smtp");
		    tr.connect(smtpHost, username, password);
		    message.saveChanges();
		 
		    
		    tr.sendMessage(message,message.getAllRecipients());
		    tr.close();
		} 
	    catch (AddressException e) 
	    {
			e.printStackTrace();
		} 
	    catch (MessagingException e) 
	    {
			e.printStackTrace();
		}
	    
	   
	 
	  }


	
	@Override
	public Utilisateur findUser(String log,String password)
	{
	
			  //getSingleResult() 
			  //Execute a SELECT query that returns a single untyped result.
			String requete=" SELECT l "
					+ " FROM Utilisateur l"
					+ " WHERE  l.login=:log"
					+ " AND l.password=:password";
			Utilisateur us=(Utilisateur) getEntityManager().createQuery(requete).setParameter("log", log).setParameter("password", password).getSingleResult();
			return  us;
				
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> findUserByAgence(int code_agence)
	{
		//getSingleResult() 
		//Execute a SELECT query that returns a single untyped result.
		String requete=" SELECT l "
					+ " FROM Utilisateur l"
					+ " WHERE  l.agence.codeAgence=:code_agence";
	Query query=getEntityManager().createQuery(requete).setParameter("code_agence", code_agence);
	return query.getResultList();
				
	}

	
	
	
}
