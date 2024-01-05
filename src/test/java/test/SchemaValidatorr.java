package test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;



public class SchemaValidatorr {




    @Test
	public void jsonSchemaValidator() {

      given()
      
      .when().get("http://localhost:3000/store")
      
      .then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeschemagen.json"));
	}
    
    
    @Test
    public void Xmlschemavalidat(){
    	
    	 given()
         
         .when().get("http://restapi.adequateshop.com/api/Traveler")
         
         .then().assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlschema.xsd"));
    	
    }


}
