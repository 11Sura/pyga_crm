package com.pyga.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class InsertDataIntoDB {
	

	public static void main(String[] args) throws SQLException {
		Connection conn=null;
		try {
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		conn = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects","root@%","root");
		Statement state = conn.createStatement();
		String query = "insert into project values('TY_PROJ_4321','Sushma','29/06/2023','TYSS','Created','3')";
	int res = state.executeUpdate(query);
	if(res==1) {
		System.out.println("created");
	}
	else {
		System.out.println("not created");
	}
		
		}
	finally {
		conn.close();
	}

	}

}
