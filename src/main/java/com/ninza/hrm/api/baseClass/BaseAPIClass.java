package com.ninza.hrm.api.baseClass;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericutilities.DataBaseUtility;
import com.ninza.hrm.api.genericutilities.FileUtility;
import com.ninza.hrm.api.genericutilities.JavaUtility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass {
	public JavaUtility jlib=new JavaUtility();
	public FileUtility flib=new FileUtility();
	public DataBaseUtility dblib=new DataBaseUtility();
	public static RequestSpecification reqSpecObj;
	public static ResponseSpecification resSpecObj;
	@BeforeSuite
	public void configBS() throws SQLException, IOException {
		dblib.getDbConnection();
		System.out.println("========connect to DB===========");
		RequestSpecBuilder Reqbuilder=new RequestSpecBuilder();
		Reqbuilder.setContentType(ContentType.JSON)
//		.setAuth(basic("username", "password"))
//		.addHeader("", "")
		.setBaseUri(flib.getDataFromPropertiesFile("BASEUri"));
		 reqSpecObj=Reqbuilder.build();
		 ResponseSpecBuilder resbuilder=new ResponseSpecBuilder();
		 resbuilder.expectContentType(ContentType.JSON);
		 resSpecObj=resbuilder.build();
		
	}
	@AfterSuite
	public void configAS() throws SQLException {
		dblib.closeConnection();
		System.out.println("========Disconnect to DB===========");

	}
}
