package Request;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.sun.istack.logging.Logger;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateUserDetails extends PropertyFileClass {
	
	public final static Logger logger = Logger.getLogger(UpdateUserDetails.class);
	@Test(priority = 4)
	public static void UpdateUserdetails() {
		
		CreateNewUser.CreateUser();

		logger.info("------------------Updation Of User's Data Begins------------------");

		RestAssured.baseURI = prop.getProperty("URI");
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("content-type", "application/json")
				.header("Authorization", prop.getProperty("Token")).body(DataConversion.PayloadDataForUpdate())
				.put("/public-api/users/" + CreateNewUser.Id);

		JsonPath body = DataConversion.rawToJson(response);
		int statusCode = body.get("code");
		Assert.assertEquals(statusCode, 200);
		Reporter.log("Updated UserEmail: " + body.get("data.email"), true);
		
		
		RestAssured.baseURI = prop.getProperty("URI");
		RequestSpecification httpRequest1 = RestAssured.given();
		Response response1 = httpRequest1.when().header("content-type", "application/json")
				.header("Authorization", prop.getProperty("Token"))
				.delete(prop.getProperty("BaseURI1")+CreateNewUser.Id);
		
		response1.prettyPeek();
		

		logger.info("------------------Updation Of User's Data Ends------------------");

	}

	@Test(priority = 5)
	public static void UpdateUserdetailsNegative() {

		logger.info("------------------Updation Of User's(Negative) Data Begins------------------");

		RestAssured.baseURI = prop.getProperty("URI");
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("content-type", "application/json")
				.header("Authorization", prop.getProperty("Token")).body(DataConversion.PayloadDataForInvalidUpdate())
				.put("/public-api/users/110");

		JsonPath body = DataConversion.rawToJson(response);
		int statusCode = body.get("code");
		Assert.assertEquals(statusCode, 422);
		Reporter.log("Status Code is : "+statusCode, true);

		logger.info("------------------Updation Of User's(Negative) Data Ends------------------");

	}
}
