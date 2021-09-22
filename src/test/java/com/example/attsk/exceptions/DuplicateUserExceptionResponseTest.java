package com.example.attsk.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class DuplicateUserExceptionResponseTest 
{
	public static final String MESSAGE = "there";
	
	@Test
	void getUserMatricola()
	{
		//when
		DuplicateUserExceptionResponse response = new DuplicateUserExceptionResponse(MESSAGE);
		//then
		assertEquals(MESSAGE, response.getUserMatricola());
	}
}
