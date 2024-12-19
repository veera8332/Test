package com.ninza.hrm.api.genericutilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

/**
 * 
 * @author barsha
 *
 */
public class DataBaseUtility {
	FileUtility flib = new FileUtility();

	// declaring the connection globally bcz we will use this in connection closing
	// statement
	Connection conn;
	Statement stat;

	public void getDbConnection(String url, String un, String pwd) throws SQLException {
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			conn = DriverManager.getConnection(url, un, pwd);
		} catch (Exception e) {
		}
	}

	public void getDbConnection() throws SQLException {
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			conn = DriverManager.getConnection(flib.getDataFromPropertiesFile("DBUrl"),
					flib.getDataFromPropertiesFile("DB_Username"), flib.getDataFromPropertiesFile("DB_Password"));
		} catch (Exception e) {
		}
	}

	public void closeConnection() throws SQLException {
		try {
			conn.close();
		} catch (Exception e) {
		}
	}

	public ResultSet ExecuteSelectQuery(String Query) throws SQLException {
		ResultSet result = null;
		try {
			stat = conn.createStatement();
			result = stat.executeQuery(Query);
		} catch (Exception e) {
		}
		// this resultset object has complete table
		return result;
	}

	public int ExecuteNonSelectQuery(String query) throws SQLException {
		int result = 0;
		try {
			stat = conn.createStatement();
			result = stat.executeUpdate(query);
			// it will return 0=query execution got fail or +1or-1=execution happened
		} catch (Exception e) {
		}
		return result;
	}
	public boolean executeQueryVerifyAndGetData(String query,int columName,String expectedData) throws SQLException {
		boolean flag=false;
		ResultSet resultset=conn.createStatement().executeQuery(query);
		while(resultset.next()) {
			//verifying employee name
			if(resultset.getString(columName).equals(expectedData)) {
				flag=true;
				break;
			}
		}
		if (flag) {
			System.out.println(expectedData +"===>data verified in data base table");
			return true;
		}
		else {
			System.out.println(expectedData +"===>data verified in data base table");
			return false;

		}
	
	}
}
