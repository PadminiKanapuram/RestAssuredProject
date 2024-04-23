package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {
	
	
	//Method to create new users. Note this method will be repeated based on the number of rows in the excel
	@Test(priority=1, dataProvider="getData", dataProviderClass= DataProviders.class )
	public void testPostUser(String UserId, String UserName, String FirstName, String LastName, String Email, String Password, String Phone) // arguments matching to the excel columns
	{
		User userpayload = new User();
		
		//Note: parameters passed name should be same as its defined in excel.
		
		userpayload.setId(Integer.parseInt(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FirstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		
		Response response = UserEndPoints.createUser(userpayload);
		Assert.assertEquals(response.getStatusCode(),200);
	}
    	
	
	@Test(priority=2, dataProvider = "getUserNames", dataProviderClass= DataProviders.class)
	public void testDeleteUser(String UserName)
	{
		//This method is responsible to delete user
		
		Response response = UserEndPoints.deleteUser(UserName);
		Assert.assertEquals(response.getStatusCode(),200);
					
	}
}
