package com.iset.Test;

import java.sql.SQLException;

import com.iset.Methodes.DeltaOracle;

public class Test {

	public static void main(String[] args) {
		DeltaOracle oracle=new DeltaOracle();
		
		try {
			oracle.nocheck();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//oracle.afficherCaisse();
		//oracle.afficherEvuti();
		
		
	}

}
