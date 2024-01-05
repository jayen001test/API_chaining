package chainingAPI;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;


public class UpdateUser {

	
	@Test
	public void test_updateuser(ITestContext context) {
		
		 Faker faker=new Faker();
		 JSONObject data=new JSONObject();
		  
		 data.put("name", faker.name().fullName());
		 data.put("gender", "male");
		 data.put("email", faker.internet().emailAddress());
		 data.put("status", "active");
		 
		// String bearertoken= "960b269d2431f9db8d374aa9e24fc62df63519b83ad10cc1eb52244e75ba026e";
		 
		 String token=context.getSuite().getAttribute("bearertoken").toString();
		 
		// int id=(Integer) context.getAttribute("userid");
		 
		 int id=(Integer) context.getSuite().getAttribute("userid");
		 given()
		  .headers("Authorization","Bearer "+token)
		  .contentType("application/json")
		  .body(data.toString())
		  .pathParam("user_id", id)
		 
		 .when()
		 .put("https://gorest.co.in/public/v2/users/{user_id}")
		
		 .then()
		 .statusCode(200)
		 .log().all();
	
		
	}
}
