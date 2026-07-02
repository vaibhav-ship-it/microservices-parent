/**
 * 
 */
package com.register.netbanking.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.register.netbanking.dto.UserDto;
import com.register.netbanking.entity.Account;
import com.register.netbanking.entity.User;
import com.register.netbanking.exceptions.DuplicateUserException;
import com.register.netbanking.repository.UserRepository;

/**
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder)	{
		this.userRepository = repository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public UserDto createUser(UserDto userDetails, Account account) throws DuplicateUserException	{
		if(userRepository.existsByMobile(userDetails.getMobile()))	{
			throw new DuplicateUserException("User with this mobile number already exists");
		}
		if(userRepository.existsByEmail(userDetails.getEmail()))	{
			throw new DuplicateUserException("User with this email address already exists");
		}
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		//userDetails.setAccountNo(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User mappedUser = modelMapper.map(userDetails, User.class);
		mappedUser.setAccount(account);
		User createdUser = userRepository.save(mappedUser);
		return modelMapper.map(createdUser, UserDto.class);
	}

}
