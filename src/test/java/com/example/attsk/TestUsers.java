package com.example.attsk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import com.example.attsk.model.*;

public class TestUsers {

	
	@Test
	public void test() {
		
		UsersDto users = new UsersDto();
		users.setId(1L);
		users.setUserName("Shahnawaz");
		users.setUserMatricola("70001");
		users.setUserPass("123456");
		users.setUserRole("ST");
		assertNotNull(users);
	}
}
