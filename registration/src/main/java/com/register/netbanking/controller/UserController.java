/**
 * 
 */
package com.register.netbanking.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.register.netbanking.dto.UserDto;
import com.register.netbanking.entity.Account;
import com.register.netbanking.exceptions.BadRequestException;
import com.register.netbanking.exceptions.DuplicateUserException;
import com.register.netbanking.model.UserRequestModel;
import com.register.netbanking.model.UserResponseModel;
import com.register.netbanking.service.AccountService;
import com.register.netbanking.service.UserService;

import jakarta.validation.Valid;

/**
 * 
 */
@RestController
@RequestMapping("/register")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	private UserService userService;
	private AccountService accountService;
	
	public UserController(UserService userService, AccountService accountService)	{
		this.userService = userService;
		this.accountService = accountService;
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody UserRequestModel userDetails,
			BindingResult bindingResult) throws BadRequestException, DuplicateUserException	{
		
		if(bindingResult.hasErrors())	{
			String errors = bindingResult.getAllErrors().stream().map(oe->oe.getDefaultMessage()).collect(Collectors.joining(", "));
			throw new BadRequestException(errors);
		}
		
		Account account = new Account();
		account.setAccountNumber(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
		account.setBalance(0L);
		account.setTransactions(new ArrayList<>());
		Account savedAccount = accountService.save(account);
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto mappedDto = mapper.map(userDetails, UserDto.class);
		UserDto createdUser = userService.createUser(mappedDto, savedAccount);
		
		UserResponseModel response = mapper.map(createdUser, UserResponseModel.class);
		
		URI locationURI = URI.create("/register/users/" + response.getUserId());
		return ResponseEntity.created(locationURI).body(response);
	}
}
