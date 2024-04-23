package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


// This is created to perform CRUD OPERATIONS on user module

public class UserEndPoints2 {
	
	
	//method created for retrieving urls from Routes.properties file
	
	static ResourceBundle getURL()
	{
		ResourceBundle routes= ResourceBundle.getBundle("Routes");// this should load properties file
		return routes;
	}
	//POST METHOD IMPLEMENTATION.
	public static Response createUser(User payload)
	{
		String post_url1 = getURL().getString("post_url");
		
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.body(payload)
							.when()
								.post(post_url1);
		return response;
		
	}
	
	public static Response getUser(String UserName)
	{
		String get_url1 = getURL().getString("get_url");
		System.out.println("Get url from propertiles- " +get_url1);
		
		Response response = given()
								.pathParam("username",UserName)
							.when()
								.get(get_url1);
		return response;
		
	}
	
	
	public static Response updateUser(String UserName,User payload)
	{
		String update_url1 = getURL().getString("update_url");
		System.out.println("update url from propertiles- " +update_url1);
		Response response = given()
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.body(payload)
								.pathParam("username", UserName)
							.when()
								.put(update_url1);
		return response;
		
	}
	
	
	public static Response deleteUser(String UserName)
	{
		String delete_url1 = getURL().getString("delete_url");
		System.out.println("Delete url from propertiles- " +delete_url1);
		Response response = given()
								.pathParam("username", UserName)
							.when()
								.delete(delete_url1);
		return response;
		
	}
	
	

}
