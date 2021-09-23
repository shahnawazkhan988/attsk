package com.example.attsk.exceptions;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserIdExceptions extends RuntimeException {
	
	public UserIdExceptions(String message) 
	{
		super(message);
	}
	
	
}
