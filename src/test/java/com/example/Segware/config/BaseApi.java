package com.example.Segware.config;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

import org.junit.BeforeClass;

public class BaseApi {
	@BeforeClass
	public static void preCondition() {
		baseURI = "http://localhost";
		basePath = "/";
		port = 8080;
	}
}
