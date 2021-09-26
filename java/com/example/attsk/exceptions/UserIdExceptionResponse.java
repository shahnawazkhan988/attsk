package com.example.attsk.exceptions;



public class UserIdExceptionResponse {

    private final String userMatricola;

    public UserIdExceptionResponse(String userMatricola) {
        this.userMatricola = userMatricola;
    }

    public String getUserMatricola() {
    	return userMatricola;
		
	}
    
}
