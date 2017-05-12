package com.iset.secoursEJB.interfaces;

import java.util.List;
import com.iset.secoursEJB.entities.Operation;

public interface OperationImpl {

	public List<Operation> findAll() ;
	public Operation findByCode(int codeop);
	boolean insertOperation(Operation operation);
		
}
