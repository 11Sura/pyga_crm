package com.pyga.practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderExample {
	
	@Test(dataProvider = "vetPatients")
	public void patientDetails(String name,String age,String type,String status) {
		System.out.println(name+" "+age+" "+type+" "+status);
		
	}
	
	@DataProvider
	public Object[][] vetPatients(){
		Object[][] obj = new Object[3][4];
		
		obj[0][0]="Sheero";
	    obj[0][1]="4";
	    obj[0][2]="Dog";
	    obj[0][3]="healthy";
	    
	    obj[1][0]="Cassie";
	    obj[1][1]="2";
	    obj[1][2]="Cat";
	    obj[1][3]="admitted";
	    
	    obj[2][0]="Gold";
	    obj[2][1]="1";
	    obj[2][2]="Parrot";
	    obj[2][3]="discharged";
				
		return obj;
	}

}
