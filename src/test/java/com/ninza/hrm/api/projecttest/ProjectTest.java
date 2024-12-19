package com.ninza.hrm.api.projecttest;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.sql.SQLException;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseAPIClass;
import com.ninza.hrm.api.pojoclass.Project_POJO;
import com.ninza.hrm.constants.endpoints.IEndPoint;

import io.restassured.response.Response;

public class ProjectTest extends BaseAPIClass{
	
	Project_POJO pobj;
	@Test
	public void addSingleProjectWithCreatedTest() throws SQLException, IOException {
//		Random rm=new Random();
//		int ran_num=rm.nextInt(1000);
		
		String exp_msg="Successfully Added";
		String projectName="apptc"+jlib.getRandomNum();
		//create an object for pojo class
		pobj=new Project_POJO("Umme",projectName,"created",0);
		//verify the project name in API layer
		Response resp=given()
			.spec(reqSpecObj)
			.body(pobj)
		.when()
			.post(IEndPoint.addProj);
		resp.then()
			.assertThat().statusCode(201)
			.assertThat().time(Matchers.lessThanOrEqualTo(3000L))
			.spec(resSpecObj)
			.log().all();
		String act_Msg=resp.jsonPath().get("msg");
		Assert.assertEquals(exp_msg, act_Msg);
		//verify the project name in DB layer
		boolean flag=dblib.executeQueryVerifyAndGetData("select * from project", 4, projectName);
		Assert.assertTrue(flag,"project in DB is not verified");

	}
	@Test(dependsOnMethods = "addSingleProjectWithCreatedTest")
	public void addDuplicateProject() throws IOException {
		//two ways we can do again create and again try to execute same api with same project name and another way is use the 
		//1st tc project name in 2nd tc

		given()
		.spec(reqSpecObj)
		.body(pobj)
	.when()
		.post(IEndPoint.addProj)
	.then()
		.assertThat().statusCode(409)
		.spec(resSpecObj)
		.log().all();
		
	}
	
}
