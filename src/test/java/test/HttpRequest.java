package test;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import javax.management.InvalidApplicationException;

public class HttpRequest {


	//@Test
	public void getUser() {

		given()

		.when()
		.get("https://reqres.in/api/users?page=2")

		.then()
		.statusCode(200).body("per_page", equalTo(6)).log().all();
	}


	//@Test
	public void createuser() {

		HashMap data=new HashMap();
		data.put("name", "Jayendra");
		data.put("job", "IT");

		given().contentType("application/json").body(data)

		.when().post("https://reqres.in/api/users")

		.then()	
		.statusCode(201).log().all();
	}


	public void pojo_demo() {

		POJO_request pr=new POJO_request();
		pr.setName("Jayendra");
		pr.setLocation("London");
		pr.setJob("Sr.Test Automation");

		given().contentType("application/json")

		.when().post("")

		.then();




	}


	//@Test
	public void getcookies() {


		Response res=given()

				.when().get("https://www.google.com/");
		Map<String, String> cookies=res.getCookies();

		System.out.println(cookies.keySet());
		for (String k : cookies.keySet()) {

			String cookies_val=res.getCookie(k);
			System.out.println( k+ "=========>"+cookies_val );
			
			

		}
	}


	//@Test
	public void getheaders() {
		
		
		Response res=given()
		.when().get("https://www.google.com/");
		
		// To get all the headers 
		
		Headers hd=res.getHeaders();
		
		// To get the header name 
		
		//header.getName()
		
		// To get the header value 
		
		//header.getValue()
		
		for (Header header : hd) {
			
			System.out.println(header.getName() +"   "+header.getValue());
			
		}
	}
	
	
	//@Test
	public void logdemo() {
		
		given()
		.when().get("https://www.google.com/")
		.then()
		//.log().all()           // To log all the information
//		.log().body()            // To get the body section
	//		.log().cookies();   // To log all the cookies
	    .log().headers();           //To log all the headers
	}
	
	
	
	@Test
	public void parseJson() {
		
		Response res=given()
		.when().get("http://localhost:3000/store");
		
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
		String bookname= res.jsonPath().getString("book[0].title");
		
		String bookname2=res.jsonPath().get("book[1].title").toString();
		
		String responseb=res.print();
		
		System.out.println(responseb);
		
		Assert.assertEquals(bookname, "Sayings of the Century");
		
		Assert.assertEquals(bookname2, "Sword of Honour");
		
		
		
		
	}
	
	
	@Test
	public void parseJsonobject() {
		
		Response res=given()
		.when().get("http://localhost:3000/store");
		
		
//		Assert.assertEquals(res.getStatusCode(), 200);
//		
//		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
//		
//		String bookname= res.jsonPath().getString("book[0].title");
//		
//		String bookname2=res.jsonPath().get("book[1].title").toString();
//		
//		String responseb=res.print();
//		
//		System.out.println(responseb);
//		
//		Assert.assertEquals(bookname, "Sayings of the Century");
//		
//		Assert.assertEquals(bookname2, "Sword of Honour");
//		
		
		//JSONObject JO=new JSONObject(res.toString());
		
		boolean status =false;
		
		JSONObject JO = new JSONObject(res.asString());
		
		for(int i=0; i<JO.getJSONArray("book").length(); i++) {
			
			String booktitle =JO.getJSONArray("book").getJSONObject(i).get("title").toString();
			
			System.out.println("*******************");
			
			System.out.println(booktitle);
			
			if(booktitle.equalsIgnoreCase("Sword of Honour")) {
				status=true;
				break;
			}
		}
		Assert.assertEquals(status, true);
	}

}
