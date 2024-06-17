package Request;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class DataConversion extends PropertyFileClass {


	public static XmlPath rawToXML(Response r) {
		String response = r.asString();
		XmlPath x = new XmlPath(response);
		r.prettyPeek();
		return x;
	}

	public static JsonPath rawToJson(Response r) {
		String response = r.asString();
		JsonPath x = new JsonPath(response);
		r.prettyPeek();
		return x;
	}

	public static Map<String, String> payload(){
		
		Map<String, String>map = new HashMap<String, String>();
		map.put("name", prop.getProperty("Name"));
		map.put("gender", prop.getProperty("Gender"));
		map.put("email", GetRandomString()+"@gmail.com");
		map.put("status", prop.getProperty("Status"));
		return map;
	}

	public static Map<String, String> PayloadDataForUpdate() {
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("email", GetRandomString() + "@gmail.com");
		return map1;
	}

	public static Map<String, String> PayloadDataForInvalidUpdate() {
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "");
		return map2;
	}

	public static String GetRandomString() {

		int lowerLimit = 97;

		int upperLimit = 122;

		Random random = new Random();

		StringBuffer r = new StringBuffer(10);

		for (int i = 0; i < 10; i++) {

			int nextRandomChar = lowerLimit

					+ (int) (random.nextFloat()

							* (upperLimit - lowerLimit + 1));

			r.append((char) nextRandomChar);

		}

		return r.toString();

	}

}
