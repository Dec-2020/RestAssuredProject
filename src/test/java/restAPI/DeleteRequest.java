package restAPI;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRequest {
	
	@Test
	public void test1(){
		
		RestAssured.baseURI = "http://54.196.179.79:8088/employees";
		RequestSpecification request = RestAssured.given();
		Response response = request.delete("/3");
		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);
		int responseCode = response.getStatusCode();
		Assert.assertEquals(responseCode, 200);
	}

}
