package com.iset.ClientSecours.managedbean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.iset.secoursEJB.entities.Operation;
import com.iset.secoursEJB.interfaces.OperationImpl;


@SuppressWarnings("serial")
@ManagedBean(name="operationBean")
@SessionScoped
public class OperationBean implements Serializable{

	@EJB
	private OperationImpl  daoOperation;
	
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
}
