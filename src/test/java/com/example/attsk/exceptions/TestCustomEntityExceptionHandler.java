package com.example.attsk.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.http.*;

public class TestCustomEntityExceptionHandler {

	private final String message = "Hello";
	DuplicateUserExceptionResponse response = new DuplicateUserExceptionResponse(message);

	CustomEntityExceptionHandler customEntityExceptionHandler = new CustomEntityExceptionHandler();

	@Test
	void handleDuplicateUserExp() throws NoSuchFieldException, NoSuchMethodException {
//	        When
		ResponseEntity<Object> responseEntity = customEntityExceptionHandler
				.handleDuplicateUserExp(new DuplicateUserExceptions(message));

//	       Then
		assertNotNull(responseEntity.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals(response.getClass().getDeclaredField("userMatricola"),
				responseEntity.getBody().getClass().getDeclaredField("userMatricola"));
		assertEquals(response.getClass().getDeclaredMethod("getUserMatricola"),
				responseEntity.getBody().getClass().getDeclaredMethod("getUserMatricola"));
	}

}
