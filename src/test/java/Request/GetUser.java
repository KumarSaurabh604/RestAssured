package Request;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.sun.istack.logging.Logger;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetUser extends PropertyFileClass {
	
	public final static Logger logger = Logger.getLogger(GetUser.class);
	@Test(priority = 2)
	public static void GetNewUser() {
		
		CreateNewUser.CreateUser();
		
		logger.info("------------------Getting Of User Begins------------------");

		Reporter.log("User Id is: " + CreateNewUser.Id, true);
		RestAssured.baseURI = prop.getProperty("URI");
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("content-type", "application/json")
				.header("Authorization", prop.getProperty("Token")).get("/public-api/users/" + CreateNewUser.Id);

		JsonPath body = DataConversion.rawToJson(response);
		int statusCode = body.get("code");
		Assert.assertEquals(statusCode, 200);
		Reporter.log("User Name is : " + body.get("data.name"), true);

		RestAssured.baseURI = prop.getProperty("URI");
		RequestSpecification httpRequest1 = RestAssured.given();
		Response response1 = httpRequest1.when().header("content-type", "application/json")
				.header("Authorization", prop.getProperty("Token"))
				.delete(prop.getProperty("BaseURI1")+CreateNewUser.Id);
		response1.prettyPeek();
		
		
		logger.info("------------------Creation Of New User Ends------------------");

	}
	
//	
	@Test(priority = 3)
	public static void GetNewUserNegative() {

		logger.info("------------------Getting Of User(Negative) Begins------------------");

		RestAssured.baseURI = prop.getProperty("URI");
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("content-type", "application/json")
				.header("Authorization", prop.getProperty("Token")).get(prop.getProperty("BaseURI1") + prop.getProperty("WrongId"));//Wrong Id is given

		JsonPath body = DataConversion.rawToJson(response);
		int statusCode = body.get("code");
		Assert.assertEquals(statusCode, 404);
		Reporter.log(("Status Code is: "+ statusCode), true);

		logger.info("------------------Creation Of New User(Negative) Ends------------------");

	}
}
