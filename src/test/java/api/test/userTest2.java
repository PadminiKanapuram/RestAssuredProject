package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints2;
import api.payloads.User;
import io.restassured.response.Response;

public class userTest2 {
	
	Faker faker;
	User userpayload;
	public static Logger logger;
	
	
	@BeforeClass
	public void setupUserData() // this class will be reponsibile to generate data
	{
		faker = new Faker();
		userpayload = new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		//log4j setup
		
		logger = LogManager.getLogger(userTest2.class);
		
		
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
		logger.info("**************User creation Started***********");
		System.out.println(userpayload.getUsername());
		Response response = UserEndPoints2.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(response.getHeader("Content-Type"),"application/json");
		
		logger.info("*************User creation Completed***************");
			
		
	}
	
	@Test(priority =2)
	public void getUserByName()
	{
		System.out.println(this.userpayload.getUsername());
		Response response = UserEndPoints2.getUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********Retrieval of user by names completed **********");
		
	}
	
	@Test(priority = 3)
	public void updateUserByName()
	{
		//update data using 
		System.out.println("*****************Update User By Name**************************");
		
		System.out.println("Before update User firstName :" +userpayload.getFirstName());
		System.out.println("Before update User LastName  :" +userpayload.getLastName());
		
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		
	
		Response ResponseAfterUpdate= UserEndPoints2.updateUser(this.userpayload.getUsername(), userpayload);
		
		System.out.println("Updated firstName :" +userpayload.getFirstName());
		System.out.println("Updated lastName :" +userpayload.getLastName());
		
		ResponseAfterUpdate.then().log().all();
		
		//validate updation post udpate existing user details
		Response Response1 = UserEndPoints2.getUser(this.userpayload.getUsername());
		Response1.then().log().all();
		
		logger.info("********Updation of user completed **********");
		
	}
	
	@Test(priority = 4)
	public void deletUserByName()
	{
		System.out.println("User to be deleted " + this.userpayload.getUsername());
		Response response= UserEndPoints2.deleteUser(this.userpayload.getUsername());
		response.then().statusCode(200);
		logger.info("********Deletion of user completed **********");
		
	}
	
}
