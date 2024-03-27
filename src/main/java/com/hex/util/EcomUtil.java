package com.hex.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is the DBUtil class which is used to Connect MySQL Database.
 */

public class EcomUtil {
	
	/**
	   * This is the DBUtil class which is used to Connect MySQL Database.
	   */
	
	static Connection com;
	public static Connection getDBConn() throws SQLException {
		
		com=DriverManager.getConnection("jdbc:mysql://localhost:3306/casestudy","root","root");
		return com;
	}
public static void main(String[]args) throws SQLException {
	System.out.println(getDBConn());
}

}
