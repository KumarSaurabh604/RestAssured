package Request;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.sun.istack.logging.Logger;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteUserDetail extends PropertyFileClass {
	
	public final static Logger logger = Logger.getLogger(DeleteUserDetail.class);
	@Test(priority = 6)
	public static void DeleteUserMethod()
	{
	logger.info("------------------Deletion Of User's Data Begins------------------");
		CreateNewUser.CreateUser();
		RestAssured.baseURI = prop.getProperty("URI");
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("content-type", "application/json")
				.header("Authorization", prop.getProperty("Token"))
				.delete(prop.getProperty("BaseURI1")+CreateNewUser.Id);
		
		JsonPath body = DataConversion.rawToJson(response);	
		int statusCode = body.get("code");
		Assert.assertEquals(statusCode,204);
		Reporter.log("User is Deleted successfully" , true);

		logger.info("------------------Deletion Of User's Data Begins------------------");
	}

}
