/**
 * 
 */
package com.login.netbanking.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 */
@ControllerAdvice
public class LoginExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handlerUserNotFoundException(UserNotFoundException e)	{
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now().toString(),
				e.getMessage(), HttpStatus.NOT_FOUND.name());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleException(Exception e)	{
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now().toString(),
				e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.name());
		return ResponseEntity.internalServerError().body(response);
	}
}
