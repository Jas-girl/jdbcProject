package com.bms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilityClass {
static String url="jdbc:mysql://localhost:3306/";
static String username="root";
static String password="root";
public static  Connection getConnection() throws SQLException {
	return DriverManager.getConnection(url, username, password);
}
}