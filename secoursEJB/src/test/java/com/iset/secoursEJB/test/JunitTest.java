package com.iset.secoursEJB.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.iset.secoursEJB.entities.Client;
import com.iset.secoursEJB.interfaces.ClientLocal;


public class JunitTest {

	
	
	private  ClientLocal clientLocal;
	@Before
	public void setUp() throws Exception {
	
	}
	
	@Test
	public void findAllTest() {

		try 
		{
			List<Client> clients=clientLocal.findAll();
			System.out.println(clients.size());
		} 
		catch (Exception e) {
			System.out.println("erreur\n"+e);
		}

	}
}
