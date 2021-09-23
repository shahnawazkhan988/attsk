package com.example.attsk.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class UserIdExceptionResponseTest 
{
	static final String MESSAGE = "there";
	
	@Test
	void getUserMatricola()
	{
		//when
		UserIdExceptionResponse response = new UserIdExceptionResponse(MESSAGE);
		//then
		assertEquals(MESSAGE, response.getUserMatricola());
	}
}
