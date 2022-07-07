package restBDD;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PutRequest {
	
	@Test
	public void PutReq() {
		JSONObject jobj =  new JSONObject();
		jobj.put("name", "Pranit");
		jobj.put("salary", "3000");
		
		RestAssured.given()
					.baseUri("http://54.196.179.79:8088/employees")
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(jobj.toString())
					.when()
					.put("/1")
					.then()
					.log()
					.status()
					.statusCode(201)
					.body("name", Matchers.equalTo("Pranit"));
	}

}
