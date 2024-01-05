package test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParseXML {



	@Test
	public void testXMLResponse() {

		Response res=given()
				.when().get("http://restapi.adequateshop.com/api/Traveler?page=1");


		XmlPath xmlObj=new XmlPath(res.asString());

		List<String> tlis=xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.");

		int tcount=tlis.size();

		System.out.println(tcount);
		
		Assert.assertEquals(tcount, 10);
		
        List<String> tnames=xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");

		boolean status=false;

		for(String tna:tnames) {

			System.out.println(tna);

			if(tna.equalsIgnoreCase("vano")) {

				status=true;

				break;
			}
		}

		Assert.assertEquals(status, true);
	}

}
