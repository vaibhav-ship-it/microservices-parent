/**
 * 
 */
package com.login.netbanking.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.login.netbanking.entity.User;
import com.login.netbanking.exceptions.UserNotFoundException;
import com.login.netbanking.repository.UserRepository;

/**
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository)	{
		this.userRepository = userRepository;
	}
	@Override
	public User findUserByEmail(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		User user = userOptional.orElseThrow(()->new UserNotFoundException("User with " + email + " does not exist."));
		/*ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(user, UserDto.class);*/
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username)
				.orElseThrow(()->new UserNotFoundException("User with " + username + " email does not exist"));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPassword(), 
			true, true, true, true, new ArrayList<>());
	}
}
