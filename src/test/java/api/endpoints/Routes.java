package api.endpoints;

/*
 This class is to maintain URL of endpoint's;
 Swagger URI  --- https://petstore.swagger.in
  
 create{POST} - https://petstore.swagger.io/v2/user
 GetUser{GET} - https://petstore.swagger.io/v2/user/{username}
 UpdateUser{PUT} - https://petstore.swagger.io/v2/user/{username}
 DeleteUser{DELETE} - https://petstore.swagger.io/v2/user/{username}
 
 
 
 */
public class Routes {

	public static String base_url = "https://petstore.swagger.io/v2"; // base url 
	
	//user module
	public static String post_url = base_url +"/user"; // base url + path parameter
	public static String get_url = base_url +"/user/{username}";
	public static String put_url = base_url +"/user/{username}";
	public static String delete_url = base_url +"/user/{username}";
	
	//store module
	   // store module url's
	//pet module 
	   // pet module url's
	
}
