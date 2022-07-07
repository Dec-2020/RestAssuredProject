package restAPIXML;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;

public class XMLTest {
	
	@Test
	public void GetReq() {
		RestAssured.given()
					.baseUri("https://chercher.tech/sample/api/books.xml")
					.when()
					.get()
					.then()
					.log()
					.body()
					.statusCode(200);
	}
	
	@Test
	public void GetReq1() {
		Response response = RestAssured.given()
					.baseUri("https://chercher.tech/sample/api/books.xml")
					.when()
					.get();
		
		NodeChildrenImpl books = response.then().extract().path("bookstore.book.title");
		System.out.println("all books:" + books.toString());
		System.out.println("1st book:" + books.get(0).toString());
		System.out.println("Language of first book :" + books.get(0).getAttribute("lang"));
		
		for(int i=0; i<books.size();i++) {
			System.out.println(books.get(i).toString());
		}
		
		for(String i : books) {
			System.out.println(i);
		}
		
		NodeChildrenImpl price = response.then().extract().path("bookstore.book.price");
		System.out.println("price:" + price.toString());
		System.out.println("Price child:" + price.get(0).children().get("paperback"));
	}

}
