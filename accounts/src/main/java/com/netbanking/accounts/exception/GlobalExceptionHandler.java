/**
 * 
 */
package com.netbanking.accounts.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private DateTimeFormatter exceptionTimestampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleAccountNotFoundException(AccountNotFoundException e)	{
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimestamp(LocalDateTime.now().format(exceptionTimestampFormatter));
		exceptionResponse.setMessage(e.getMessage());
		exceptionResponse.setHttpStatus(HttpStatus.NOT_FOUND.name());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException e)	{
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimestamp(LocalDateTime.now().format(exceptionTimestampFormatter));
		exceptionResponse.setMessage(e.getMessage());
		exceptionResponse.setHttpStatus(HttpStatus.BAD_REQUEST.name());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleException(Exception e)	{
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setTimestamp(LocalDateTime.now().format(exceptionTimestampFormatter));
		exceptionResponse.setMessage(e.getMessage());
		exceptionResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
	}
}
