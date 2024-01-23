package com.birlasoft.libraryms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBOracleConnection {
	
	public static void loadDrivers() {
		// Loads the required drivers for the connection
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// System.out.println("drivers loaded");

		} catch (ClassNotFoundException e) {
			System.out.println("drivers not found");
		}
	}

	public static Connection getConnection() {
		// Returns a connection for the DAO process.
		loadDrivers();
		String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "sys as sysdba";
		String password = "welcome12#";
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, username, password);
			// System.out.println("connect object: " + c);
		} catch (SQLException e) {
			System.out.println("Problem in connection" + e);
		}
		return c;
	}

	public static void closeConnection(Connection c) {
		// Close a connection given as parameter
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("Problem in connection" + e);
		}
	}
}
