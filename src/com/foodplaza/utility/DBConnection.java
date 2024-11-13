package com.foodplaza.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection establishConnection() throws SQLException, ClassNotFoundException{
	    String url,user,password;
		
		url = "jdbc:mysql://localhost:3306/food_plaza_vaibhav";
		user = "root";
		password = "200261";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection(url,user,password);
		
		return conn;
	}

}
