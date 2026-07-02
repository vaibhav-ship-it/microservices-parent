/**
 * 
 */
package com.profile.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profile.entity.User;
import com.profile.model.UserRequestModel;
import com.profile.model.UserResponseModel;
import com.profile.service.UserService;

import jakarta.validation.Valid;

/**
 * 
 */
@RestController
@RequestMapping("/profile")
//@CrossOrigin(origins = "http://localhost:4200")
public class ProfileController {
	
	private UserService userService;

	public ProfileController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/user")
	public ResponseEntity<UserResponseModel> getUserProfile(@RequestHeader("username") String username)	{
		User user = userService.findUserByEmail(username);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserResponseModel userResponseModel = modelMapper.map(user, UserResponseModel.class);
		return ResponseEntity.ok().body(userResponseModel);
	}
	
	@PutMapping("/user")
	public ResponseEntity<UserResponseModel> updateUserProfile(@Valid @RequestBody UserRequestModel userRequestModel, @RequestHeader("username") String username)	{
		User user = userService.findUserByEmail(username);
		user.setFirstName(userRequestModel.getFirstName());
		user.setLastName(userRequestModel.getLastName());
		user.setMobile(userRequestModel.getMobile());
		user.setAddress(userRequestModel.getAddress());
		User savedUser = userService.save(user);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserResponseModel userResponseModel = modelMapper.map(savedUser, UserResponseModel.class);
		return ResponseEntity.ok().body(userResponseModel);
	}
}
