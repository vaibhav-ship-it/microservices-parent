/**
 * 
 */
package com.login.netbanking.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 
 */

public class UserNotFoundException extends UsernameNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public UserNotFoundException(String message)	{
		super(message);
	}
}
