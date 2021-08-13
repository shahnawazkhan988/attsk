package com.example.attsk.exceptions;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateUserExceptions extends RuntimeException {
	
	public DuplicateUserExceptions(String message) 
	{
		// TODO Auto-generated constructor stub
		super(message);
	}
	
	
}
