package com.example.attsk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import com.example.attsk.model.*;

public class TestUsers {

	
	@Test
	public void test() {
		assertNotNull(new UsersDto(1, "Test User", "70001", "pass123", "st"));
	}
}
