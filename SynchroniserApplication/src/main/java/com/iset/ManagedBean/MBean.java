package com.iset.ManagedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;

import com.iset.Methodes.Synchronisation;

@ManagedBean(name="mBean")
@SessionScoped
public class MBean {

	public void synchro()
	{
		Synchronisation oracle=new Synchronisation();
		//String url= "jdbc:mysql://localhost:3306/deltasecours?autoReconnect=true&useSSL=false";
		
		
		
			oracle.selectSynchronisation();
		
		
		//oracle.selectSynchronisation();
	}
	public void filldAll()
	{
		
	}
}
