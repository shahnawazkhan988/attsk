package com.example.attsk.controller;


import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.web.server.*;
import org.springframework.http.*;

import com.example.attsk.dao.*;
import com.example.attsk.model.*;

import io.restassured.*;
import io.restassured.response.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersControllerTestIT 
{
	
	@Autowired
	private IUsersDao iUsersDao;
	
	@LocalServerPort
	private int port;

	@BeforeEach
	public void setup() {
		RestAssured.port = port;
		// always start with an empty database
		iUsersDao.deleteAll();
		iUsersDao.flush();
	}
	@Test
	void test_createNewUser() throws Exception
	{
		UsersDto user = new UsersDto();
		user.setId(1L);
		user.setUserName("Shahnawaz");
		user.setUserMatricola("70001");
		user.setUserPass("123456");
		user.setUserRole("ST");
		
		Response response = given().
				contentType(MediaType.APPLICATION_JSON_VALUE).
				body(user).
			when().
				post("/api/v1/new/users");

			UsersDto saved = response.getBody().as(UsersDto.class);

			// read it back from the repository
			assertThat(iUsersDao.findById(saved.getId()))
				.contains(saved);
		
	}
}
