package com.iset.secoursEJB.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.iset.secoursEJB.entities.Client;
@Remote
public interface ClientImpl {

	List<Client> findAll();
	Client findClient(int cin);
	
	Client findByCode(int codeClient);
}
