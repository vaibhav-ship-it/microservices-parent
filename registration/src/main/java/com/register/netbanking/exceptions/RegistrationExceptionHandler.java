package com.register.netbanking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.time.LocalDateTime;

@RestControllerAdvice
public class RegistrationExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimestamp(LocalDateTime.now().toString());
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setHttpStatus(HttpStatus.BAD_REQUEST.name()); // or .value()
        return ResponseEntity.badRequest().body(exceptionResponse);
    }
    
    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateUserException(DuplicateUserException e)	{
    	ExceptionResponse exceptionResponse = new ExceptionResponse();
    	exceptionResponse.setTimestamp(LocalDateTime.now().toString());
    	exceptionResponse.setMessage(e.getMessage());
    	exceptionResponse.setHttpStatus(HttpStatus.CONFLICT.name());
    	return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAll(Exception e) {
    	ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now().toString(),
    			e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.name());
    	return ResponseEntity.internalServerError().body(exceptionResponse);
    }

}
