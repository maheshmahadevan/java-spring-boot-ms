package com.example.msdemo;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.path.json.JsonPath;

@RunWith(SpringRunner.class)

public class BookTest {
	
	@MockBean
	private BookRepository bookRepository;
	private String accessToken;
	
	@Before
	public void setup() {
		Book book = new Book();
		book.setName("Midnights Children");
		book.setAuthor("Salman Rushdie");
		
		Mockito.when(bookRepository.findOne(1L)).thenReturn(book);
		
		String response =
	            given().port(9090)
	                .auth().basic("ClientId", "secret")
	                .basePath("/auth")
	                .contentType("multipart/form-data")
	                .multiPart("grant_type", "client_credentials")
	                .when()
	                .post("/oauth/token")
	                .asString();
		
		System.out.println(response);

	    JsonPath jsonPath = new JsonPath(response);
	    accessToken = jsonPath.getString("access_token");
	}
	
	@Test
	public void test_get()
	{
		given().auth().oauth2(accessToken)
		.when().get("/books").
		then().statusCode(200);
		//.body("name",equalTo("Midnights Children"));
	}
}
