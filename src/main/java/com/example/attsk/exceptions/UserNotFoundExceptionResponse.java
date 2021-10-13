package com.example.attsk.exceptions;

public class UserNotFoundExceptionResponse {
	private final String message;
	
	public UserNotFoundExceptionResponse(String message)
	{
		this.message = message;
	}
	public String getMessage()
	{
		return message;
	}
}
