package com.example.attsk.controller;


import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.web.server.*;
import org.springframework.http.*;

import com.example.attsk.dao.*;
import com.example.attsk.entities.Users;
import com.example.attsk.model.*;
import com.example.attsk.service.*;

import io.restassured.*;
import io.restassured.response.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersControllerTestIT 
{
	@Autowired
	UsersServiceImpl usersServiceImpl;
	
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
		Users user = new Users();
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

		Users saved = response.getBody().as(Users.class);

			// read it back from the repository
			assertThat(iUsersDao.findById(saved.getId()))
				.contains(saved);
		
	}
	
	@Test
	void test_getAllUsers() throws Exception {

		// given
		Users user = new Users();
		user.setId(1L);
		user.setUserName("Shahnawaz");
		user.setUserMatricola("70001");
		user.setUserPass("123456");
		user.setUserRole("ST");
		
		List<Users> allUsers = List.of(user);
		
		iUsersDao.saveAll(allUsers);
		// when
		when().
		get("/api/v1/users");
		
		assertEquals(1, iUsersDao.findAll().size());

	}
	
	@Test
	void test_deleteUser() throws Exception {

		// given
		Users user = new Users();
		user.setId(1L);
		user.setUserName("Shahnawaz");
		user.setUserMatricola("70001");
		user.setUserPass("123456");
		user.setUserRole("ST");

		List<Users> allUsers = List.of(user);
		
		iUsersDao.saveAll(allUsers);

		// when
		when().
		delete("/api/v1/70001");
				
		assertEquals(0, iUsersDao.findAll().size());

	}
	
}
