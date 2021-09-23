package com.example.attsk.exceptions;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.*;


@ControllerAdvice
public class CustomEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UserIdExceptions.class)
    protected final ResponseEntity<Object> handleDuplicateUserExp(UserIdExceptions exception){
        var response = new UserIdExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    
}
