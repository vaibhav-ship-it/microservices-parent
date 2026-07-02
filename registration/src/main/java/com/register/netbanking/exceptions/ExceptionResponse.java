/**
 * 
 */
package com.register.netbanking.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExceptionResponse {
	
	private String timestamp;
	private String message;
	private String httpStatus;

}
