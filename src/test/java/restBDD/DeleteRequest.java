package restBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DeleteRequest {
	
	@Test
	public void DelReq() {
		RestAssured.given()
		.baseUri("http://54.196.179.79:8088/employees")
		.when()
		.delete("/1")
		.then()
		.log()
		.status()
		.statusCode(200);
	}

}
