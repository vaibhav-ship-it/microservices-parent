/**
 * 
 */
package com.profile.service;

import com.profile.entity.User;

/**
 * 
 */
public interface UserService	{
	
	public User findUserByUserId(String userId);
	public User findUserByEmail(String email);
	public User findUserById(int id);
	public User save(User user);
	
}
