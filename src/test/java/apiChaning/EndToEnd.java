package apiChaning;

import org.testng.annotations.Test;
import org.json.JSONObject;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEnd {
	
	Response response;
	String baseURI = "http://54.196.179.79:8088/employees";
	
	@Test
	public void test1() {
		response = GetAllMethod();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		response = PostMethod("Pankaj","50000");
		Assert.assertEquals(response.getStatusCode(), 201);
		JsonPath Jpath =response.jsonPath();
		int EmpId = Jpath.get("id");
        System.out.println("id :- " + EmpId);
        
        response = PutMethod(EmpId,"Rick","6000");
        Assert.assertEquals(response.getStatusCode(), 200);
        Jpath =response.jsonPath();
        Assert.assertEquals(Jpath.get("name"), "Rick");
        
        response = DeleteMethod(EmpId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().asString(), "{}");
        
        response = GetMethod(EmpId);
        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(response.getBody().asString(), "{}");

	}
	
	public Response GetAllMethod() {
		RestAssured.baseURI = baseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.get();
		return response;
	}
	
	public Response PostMethod(String Name, String Salary) {
		RestAssured.baseURI = baseURI;
		JSONObject jobj =  new JSONObject();
		jobj.put("name", "Greg");
		jobj.put("salary", "3000");
		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jobj.toString()).post();
		return response;
	}
	
	public Response PutMethod(int EmpId,String Name, String Salary) {
		RestAssured.baseURI = baseURI;
		JSONObject jobj =  new JSONObject();
		jobj.put("name", Name);
		jobj.put("salary", Salary);
		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jobj.toString()).put("/"+ EmpId);
		return response;	
	}
	
	public Response DeleteMethod(int EmpId) {
		
		RestAssured.baseURI = baseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.delete("/" + EmpId);
		return response;
	}
	
	public Response GetMethod(int EmpId) {
		
		RestAssured.baseURI = baseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.get("/" + EmpId);
		return response;
	}


}














