package com.example.attsk.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserNotFoundExceptionResponseTest 
{
	@Test
	void getMessage()
	{
		//given
		String message = "here";
		//when
		UserNotFoundExceptionResponse response = new UserNotFoundExceptionResponse(message);
		//then
		assertEquals(message, response.getMessage());
	}
}
