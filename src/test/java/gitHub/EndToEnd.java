package gitHub;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEnd {

	Response response;
	
	@Test
	public void GitHubEndToEnd() throws IOException {
		response = GetAllRepo();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		response = CreateRepo();
		Assert.assertEquals(response.getStatusCode(), 201);
		JsonPath Jpath =response.jsonPath();
		int EmpId = Jpath.get("id");
		System.out.println("New ID is" + EmpId);

//		response = UpdateRepo();
//		Assert.assertEquals(response.getStatusCode(), 200);
//		
//		response = DeleteRepo();
//        Assert.assertEquals(response.getStatusCode(), 200);
//        Assert.assertEquals(response.getBody().asString(), "{}");
	}
	
	public Response GetAllRepo() {
		RestAssured.baseURI = "https://api.github.com/users/Dec-2020/repos";
		RequestSpecification request = RestAssured.given();
		Response res = request.get();
		return res;
	}
	
	public Response CreateRepo() throws IOException {
		RestAssured.baseURI = "https://api.github.com/user/repos";
		RequestSpecification request = RestAssured.given();
		byte[] dataBytes = Files.readAllBytes(Paths.get("data.json"));
		Response res = request.auth()
								.oauth2("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")
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.body(dataBytes)
								.post();
		return res;
	}
	
	public Response UpdateRepo() throws IOException {
		RestAssured.baseURI = "https://api.github.com/repos/Dec-2020/PostmanDemo";
		RequestSpecification request = RestAssured.given();
		byte[] dataBytes = Files.readAllBytes(Paths.get("data.json"));
		Response res = request.auth()
								.oauth2("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")
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.body(dataBytes)
								.put();
		return res;
	}
	

	public Response DeleteRepo() {
		RestAssured.baseURI = "https://api.github.com/repos/Dec-2020/PostmanGitHubDemo";
		RequestSpecification request = RestAssured.given();
		Response res = request.delete();
		return res;
	}
	
}

















