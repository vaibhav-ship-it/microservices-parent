/**
 * 
 */
package com.register.netbanking.service;

import com.register.netbanking.dto.UserDto;
import com.register.netbanking.entity.Account;
import com.register.netbanking.exceptions.DuplicateUserException;

/**
 * 
 */
public interface UserService	{

	//public abstract User save(User user);
	public UserDto createUser(UserDto userDetails, Account account) throws DuplicateUserException;
	
}
