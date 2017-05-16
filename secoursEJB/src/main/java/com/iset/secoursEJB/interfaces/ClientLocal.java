package com.iset.secoursEJB.interfaces;
import java.util.List;

import javax.ejb.Local;
import com.iset.secoursEJB.entities.Client;

@Local
public interface ClientLocal {

	

		List<Client> findAll();
		Client findClient(String cin);
		Client findByCode(int codeClient);
		
	
}
