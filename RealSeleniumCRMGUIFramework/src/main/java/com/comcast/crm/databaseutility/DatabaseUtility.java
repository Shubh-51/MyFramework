package com.comcast.crm.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	
	Connection conn=null; 
	public void getConnectionOfDatabase(String url,String username,String password) {
		
		try {
			//Step-1:register/load the Driver
			Driver driverreff=new Driver();
			DriverManager.registerDriver(driverreff);
			//getConnection to db
			 conn=DriverManager.getConnection(url, username,password);
		} catch (SQLException e) {
			
		}
	}
	
	public void getConnectionOfDatabase() {
		
		try {
			//Step-1:register/load the Driver
			Driver driverreff=new Driver();
			DriverManager.registerDriver(driverreff);
			//getConnection to db
			 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/vtiger", "root","root");
		} catch (SQLException e) {
			
		}
	}
	public ResultSet executeSelectQuery(String query) {
		ResultSet resultset=null;
		//create sql statement
		try {
			Statement stat=conn.createStatement();
			 resultset=stat.executeQuery(query);
			
		} catch (SQLException e) {
			
		}
	return resultset;
	}
	public int executeNonSelectQuery(String query) {
		int update=3;
		try {
			Statement stat=conn.createStatement();
			 update=stat.executeUpdate(query);
		} catch (SQLException e) {
			
		}
		return update;
	}
	
	public void closeDatabaseConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
		
		}
	}
	
	
}
