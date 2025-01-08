package com.EcommereceOrderManagementSystemsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
 static String url="jdbc:mysql://localhost:3306/";
 static String username="root";
 static String password="root";

 static public Connection getConnection() throws SQLException {
	 return DriverManager.getConnection(url, username, password);
		
 }
}
