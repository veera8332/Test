package com.ninza.hrm.api.employeetest;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.pojoclass.Employee_POJO;
import com.ninza.hrm.api.pojoclass.Project_POJO;
import com.ninza.hrm.constants.endpoints.IEndPoint;

import io.restassured.http.ContentType;

public class EmployeeTest extends BaseAPIClass{

@Test
public void addEmployeeTest() throws SQLException, IOException {
	
	String projectName="airBharat"+jlib.getRandomNum();
	String emp_name="milan"+jlib.getRandomNum();
	Project_POJO pobj=new Project_POJO("barsha",projectName,"created",0);
	given()
		.spec(reqSpecObj)
		.body(pobj)
	.when()
		.post(IEndPoint.addProj)
	.then()
	.spec(resSpecObj)
		.log().all();
	// because it is not a complex json object
	System.out.println(projectName);
	//Api-2==>Add emplyee to same project,so we have to go to swagger document ,add employee(post)
	Employee_POJO emp_obj=new Employee_POJO("architect", "24/04/1983", "barsha123@gmail.com",emp_name, 3.4, 
	"1236985479", projectName, "ROLE_EMPLOYEE", emp_name);
	given()
	.spec(reqSpecObj)
		.body(emp_obj)
	.when()
		.post(IEndPoint.addEmp)
	.then()
		.assertThat().statusCode(201)
		//.and()//instead of assert that we can use and()
		//.time(Matchers.lessThanOrEqualTo(3000L))
		.spec(resSpecObj)
		.log().all();
	
	//verify employee name in DB
	//connect to db
	boolean flag=dblib.executeQueryVerifyAndGetData("select * from employee", 5, emp_name);
	Assert.assertTrue(flag,"employee in DB is not verified");
}
@Test
public void addEmployeeWithoutEmailTest() throws SQLException, IOException {
	
	Random rm=new Random();
	int ranNum=rm.nextInt(1000);
	String projectName="airBharat"+ranNum;
	String emp_name="milan"+ranNum;
	Project_POJO pobj=new Project_POJO("barsha",projectName,"created",0);
	given()
		.contentType(ContentType.JSON)
		.body(pobj)
	.when()
		.post(IEndPoint.addProj)
	.then()
		.log().all();
	// because it is not a complex json object
	System.out.println(projectName);
	//Api-2==>Add emplyee to same project,so we have to go to swagger document ,add employee(post)
	Employee_POJO emp_obj=new Employee_POJO("architect", "24/04/1983", "",emp_name, 3.4, 
	"1236985479", projectName, "ROLE_EMPLOYEE", emp_name);
	given()
	.spec(reqSpecObj)
		.body(emp_obj)
	.when()
		.post(IEndPoint.addEmp)
	.then()
		.assertThat().statusCode(500)
		.spec(resSpecObj)
		.log().all();
}
}
