package restAPI;

import org.testng.annotations.Test;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestWithParams {
	
	@Test
	public void test1(){
		
		RestAssured.baseURI = "http://54.196.179.79:8088/employees";
		RequestSpecification request = RestAssured.given();
		Response response = request.param("id", "1").get();
		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);
		
		int responseCode = response.getStatusCode();
		Assert.assertEquals(responseCode, 200);
		
		Assert.assertTrue(ResponseBody.contains("Pankaj"));
		
		JsonPath jpath = response.jsonPath();
		List<String> names = jpath.get("name");
		
		System.out.println(names.get(0));
		Assert.assertEquals(names.get(0), "Pankaj");
		
		String Header = response.getHeader("Content-Type");
		System.out.println(Header);

	}

}
