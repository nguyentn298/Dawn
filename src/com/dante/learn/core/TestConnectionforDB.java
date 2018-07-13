package com.dante.learn.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnectionforDB {

	// jdbc:sqlserver://127.0.0.1;databaseName=Loan-Quanlity-Dev1
	// jdbc\:sqlserver\://127.0.0.1;databaseName\=Loan-Quanlity-Dev1
	public static void main(String[] args) {
		// String Url =
		// "jdbc:sqlserver://127.0.0.1;DatabaseName=Loan-Quanlity-Dev1;user=sa;Password=123456";
		// String Url = "jdbc:sqlserver://(local);user=sa;password=123456";
		Connection conn = null;
		String dbURL = "jdbc:sqlserver://localhost;DatabaseName=Loan-Quanlity-Dev1";
		String user = "sa";
		String pass = "123456";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			System.out.println("Trying to connect");
			conn = DriverManager.getConnection(dbURL, user, pass);

			System.out.println("Connection Established Successfull and the DATABASE NAME IS:"
					+ conn.getMetaData().getDatabaseProductName());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Fail!!!");
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
