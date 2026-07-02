/**
 * 
 */
package com.netbanking.accounts.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
public class ExceptionResponse {

	private String timestamp;
	private String message;
	private String httpStatus;
}
