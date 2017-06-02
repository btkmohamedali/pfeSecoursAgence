package com.iset.secoursEJB.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.iset.secoursEJB.entities.Utilisateur;

@Stateless
@Remote
public interface UtilisateurImpl {

	public boolean validateLogin(String log, String pass) ;
	public Utilisateur findByLogin(String username);
	
	public List<Utilisateur> findAllUsers();
	public List<String> findAllEmail();
	public List<String> findAllMDPUsers();
	public void Mail(String email,List<String> MDPUsers);
	
	Utilisateur findUser(String log, String password);
	List<Utilisateur> findUserByAgence(int code_agence);
	
	
}
