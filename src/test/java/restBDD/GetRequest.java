package restBDD;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequest {
	
	@Test
	public void GetReq1() {
		RestAssured.given()
					.baseUri("http://54.196.179.79:8088/employees")
					.when()
					.get("/1")
					.then()
					.log()
					.status()
					.statusCode(200);
	}
	
	@Test
	public void GetReq2() {
		RestAssured.given()
					.baseUri("http://54.196.179.79:8088/employees")
					.queryParam("id", "1")
					.when()
					.get()
					.then()
					.log()
					.body()
					.statusCode(200)
					.body("[0].name", Matchers.equalTo("Pankaj"));
	}
	
	@Test
	public void GetReq3() {
		Response response = RestAssured.given()
					.baseUri("http://54.196.179.79:8088/employees")
					.queryParam("id", "1")
					.when()
					.get();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
















































