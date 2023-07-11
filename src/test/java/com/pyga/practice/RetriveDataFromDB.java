package com.pyga.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class RetriveDataFromDB {
	public static void main(String[] args) throws SQLException {
		Connection conn=null;
		try {
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		conn = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects","root@%","root");
		Statement state = conn.createStatement();
		String query = "select * from project";
	ResultSet res = state.executeQuery(query);
	while(res.next()) {
		String value = res.getString(1)+" "+res.getString(2)+" "+res.getString(3)+" "+res.getString(4)+" "+res.getString(5)+" "+res.getInt(6);
		System.out.println(value);
	}
		}
	finally {
		conn.close();
	}

	}


}
