/**
 * 
 */
package com.login.netbanking.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.login.netbanking.dto.UserDto;
import com.login.netbanking.entity.User;

/**
 * 
 */
public interface UserService extends UserDetailsService	{

	public User findUserByEmail(String email);
	
}
