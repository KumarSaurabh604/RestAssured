package Request;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.sun.istack.logging.Logger;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateNewUser extends PropertyFileClass {

static int Id = 0;
	
	public final static Logger logger = Logger.getLogger(CreateNewUser.class);
	@Test(priority = 1)
	public static void CreateUser() {

		logger.info("------------------Creation Of New User Begins------------------");

		
		RestAssured.baseURI = prop.getProperty("URI");
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("content-type", "application/json")
				.header("Authorization", prop.getProperty("Token")).body(DataConversion.payload())
				.post(prop.getProperty("BaseURI"));
		
		JsonPath body = DataConversion.rawToJson(response);	
		int statusCode = body.get("code");
		Assert.assertEquals(statusCode,201);
		
		Id = body.get("data.id");
		Reporter.log("Generated New Id: " + Id, true);
		
		logger.info("------------------Creation Of New User Ends------------------");
		
	}
}
