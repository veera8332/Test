package com.ninza.hrm.api.genericutilities;

import static io.restassured.RestAssured.*;

import java.net.URI;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class RestAssuredUtility {
	
	Response res;
	
	
	public Response getResponseComplexData(String body, String uri) {
		return given().contentType(ContentType.JSON).body(body).post(uri);
	}
	 public String getJsonPath(String path) {
		 return res.jsonPath().get(path);
	 }
	 
	 public ValidatableResponse verifyStatusCode(int code) {
		return res.then().assertThat().statusCode(code);
	 }
	 public ValidatableResponse verifyHeader(String headerName, String headerValue) {
		return res.then().header(headerName, Matchers.equalTo(headerValue));
	 }
	 
	 public ValidatableResponse verifyStatusLine(String headerLine) {
		 return res.then().assertThat().statusLine(headerLine);
	 }
	 
	 public ValidatableResponse verifyResponseTimeLessThan(long milliSec) {
		 return res.then().assertThat().time(Matchers.greaterThan(milliSec));
	 }
	 public ValidatableResponse verifyResponseTimeGreaterThan(long milliSec) {
		 return res.then().assertThat().time(Matchers.greaterThan(milliSec));
	 }
	 public ValidatableResponse verifyResponseTimeInBetween(long milliSec, Long milliSec1, Long MilliSec2) {
		 return res.then().assertThat().time(Matchers.both(Matchers.greaterThan(milliSec1)).and(Matchers.lessThan(MilliSec2)));
	 }

}
