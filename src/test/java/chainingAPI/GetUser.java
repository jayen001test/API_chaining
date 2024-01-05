package chainingAPI;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GetUser {
	
	
	@Test
	public void test_getuser(ITestContext context) {
		
		 
		 //String bearertoken= "960b269d2431f9db8d374aa9e24fc62df63519b83ad10cc1eb52244e75ba026e";
		 
		// int id=(Integer) context.getAttribute("userid");
		 
		 String token=context.getSuite().getAttribute("bearertoken").toString();
		 int id=(Integer) context.getSuite().getAttribute("userid");
		 
	     given()
			  .headers("Authorization","Bearer "+token)
			  .pathParam("user_id", id)
		  
		 .when()
		 	.get("https://gorest.co.in/public/v2/users/{user_id}")
		 
		 .then()
			 .statusCode(200)
			 .log().all();
		
		 
	}

}
