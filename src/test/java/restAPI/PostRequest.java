package restAPI;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {
	
	@Test
	public void tes1() {
		
		RestAssured.baseURI = "http://54.196.179.79:8088/employees";
		RequestSpecification request = RestAssured.given();
		
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body("{\n" + 
								"        \"firstName\": \"Neha\",\n" + 
								"        \"lastName\": \"Naik\",\n" + 
								"        \"salary\": 100000,\n" + 
								"        \"email\": \"neha@naik.com\"\n" + 
								"}").post();
		
		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);
		
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 201);
		
		JsonPath Jpath =response.jsonPath();
        System.out.println("id"+Jpath.get("id"));

	}

}
