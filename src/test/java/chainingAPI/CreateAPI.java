package chainingAPI;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CreateAPI {

	
	
	@Test
	public void createuser(ITestContext context) {
		
		
		 Faker faker=new Faker();
		 JSONObject data=new JSONObject();
		  
		 data.put("name", faker.name().fullName());
		 data.put("gender", "male");
		 data.put("email", faker.internet().emailAddress());
		 data.put("status", "inactive");
		 
		 String token= "960b269d2431f9db8d374aa9e24fc62df63519b83ad10cc1eb52244e75ba026e";
		 
		 int id=0;
		Response res= given()
		  .headers("Authorization","Bearer "+token)
		  .contentType("application/json")
		  .body(data.toString())
		 
		 .when()
		 .post("https://gorest.co.in/public/v2/users");
		
		 id=res.jsonPath().getInt("id");
		 
		System.out.println("The ID is :"+id);
		
		//context.setAttribute("userid", id);  // This will work in single test 
		context.getSuite().setAttribute("bearertoken", token);
		context.getSuite().setAttribute("userid", id); // This will work for multple test set     
	
		 
	}
	
	
	
	
	
}
