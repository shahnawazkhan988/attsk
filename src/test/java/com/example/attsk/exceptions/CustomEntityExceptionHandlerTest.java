package com.example.attsk.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.http.*;


 class CustomEntityExceptionHandlerTest {

	private final String message = "Hello";
	UserIdExceptionResponse response = new UserIdExceptionResponse(message);

	CustomEntityExceptionHandler customEntityExceptionHandler = new CustomEntityExceptionHandler();

	@Test
	void handleUserIdExceptions() throws NoSuchFieldException, NoSuchMethodException {
		//When
		ResponseEntity<Object> responseEntity = customEntityExceptionHandler
				.handleUserIdExceptions(new UserIdExceptions(message));

		//Then
		assertNotNull(responseEntity.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals(response.getClass().getDeclaredField("userMatricola"),
				responseEntity.getBody().getClass().getDeclaredField("userMatricola"));
		assertEquals(response.getClass().getDeclaredMethod("getUserMatricola"),
				responseEntity.getBody().getClass().getDeclaredMethod("getUserMatricola"));
	}
	
	@Test
    void handleUserNotFoundException(){
//        When
        ResponseEntity<Object> responseEntity = customEntityExceptionHandler.handleUserNotFoundException(new UserNotFoundException(message));
//         Then
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());

    }
	
	

}
