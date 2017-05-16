package com.iset.ClientSecours.managedbean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.iset.secoursEJB.entities.Operation;
import com.iset.secoursEJB.interfaces.OperationImpl;
import com.iset.secoursEJB.interfaces.RemiseChequeImpl;
import com.iset.secoursEJB.interfaces.RemiseEffetImpl;
import com.iset.secoursEJB.interfaces.RetraitImpl;
import com.iset.secoursEJB.interfaces.VersementImpl;
import com.iset.secoursEJB.interfaces.VirementImpl;


@SuppressWarnings("serial")
@ManagedBean(name="operationBean")
@SessionScoped
public class OperationBean implements Serializable{

	@EJB
	private OperationImpl  daoOperation;
	
	@EJB
	private RetraitImpl  daoretrait;
	@EJB 
	private VersementImpl daoversement;
	@EJB 
	private VirementImpl daovirement;
	@EJB
	private RemiseChequeImpl daoremiseC;
	@EJB 
	private RemiseEffetImpl daoremiseE;
	
	private Operation operation=new Operation();

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	
	public List<Operation> findAll()
	{
		return daoOperation.findAll();
	}
	
	public long countRetrait()
	{
		long x=daoretrait.countNbrRetrait();
		System.out.println(x);
		return x;
	}
	
	public long countVersement()
	{
		long x=daoversement.countNbrVersement();
		System.out.println(x);
		return x;
	}
	
	public long countVirement()
	{
		long x=daovirement.countNbrVirement();
		System.out.println(x);
		return x;
	}
	
	public long countRemiseC()
	{
		long x=daoremiseC.countNbrRemiseC();
		System.out.println(x);
		return x;
	}
	
	public long countRemiseE()
	{
		long x=daoremiseE.countNbrRemiseE();
		System.out.println(x);
		return x;
	}
}
