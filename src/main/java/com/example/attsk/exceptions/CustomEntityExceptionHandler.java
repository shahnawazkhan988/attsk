package com.example.attsk.exceptions;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.*;


@ControllerAdvice
public class CustomEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(DuplicateUserExceptions.class)
    protected final ResponseEntity<Object> handleDuplicateUserExp(DuplicateUserExceptions exception){
        var response = new DuplicateUserExceptionResponse(exception.getMessage());
        System.out.println(response+"idar aya");
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    
}
