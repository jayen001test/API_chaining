package chainingAPI;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;


public class DeleteUser {

	
	@Test
	public void test_deleteuser(ITestContext context) {
		
		//String bearertoken= "960b269d2431f9db8d374aa9e24fc62df63519b83ad10cc1eb52244e75ba026e";
		
		String token=context.getSuite().getAttribute("bearertoken").toString();
		
	//	int id=(Integer) context.getAttribute("userid");
		
		 int id=(Integer) context.getSuite().getAttribute("userid");
		 
		 given()
		  .headers("Authorization","Bearer "+token)
		  .contentType("application/json")
		  .pathParam("user_id", id)
		  
		  .when()
		  .delete("https://gorest.co.in/public/v2/users/{user_id}")
		  
		  .then()
		  .statusCode(204)
		  .log().all();
		
	}
}
