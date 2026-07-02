/**
 * 
 */
package com.profile.service;

import org.springframework.stereotype.Service;

import com.profile.entity.User;
import com.profile.repository.UserRepository;

/**
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findUserByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}

	public User findUserByEmail(String email)	{
		return userRepository.findByEmail(email);
	}
	public User findUserById(int id)	{
		return userRepository.findById(id);
	}
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

}
