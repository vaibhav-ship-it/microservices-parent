/**
 * 
 */
package com.login.netbanking.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.netbanking.entity.Transaction;
import com.login.netbanking.feign.AccountFeign;
import com.login.netbanking.model.LoginRequestModel;
import com.login.netbanking.security.JwtUtil;
import com.login.netbanking.service.UserService;

/**
 * 
 */
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

	private UserService userService;
	private AccountFeign accountFeign;
	private JwtUtil jwtUtil;
	private BCryptPasswordEncoder pwdEncoder;
	private static final String dateFormat = "yyyy-MM-dd";
	
	public LoginController(UserService userService, AccountFeign accountFeign,
			JwtUtil jwtUtil, BCryptPasswordEncoder pwdEncoder)	{
		this.userService = userService;
		this.accountFeign = accountFeign;
		this.jwtUtil = jwtUtil;
		this.pwdEncoder = pwdEncoder;
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String,String>> login(@RequestBody LoginRequestModel loginRequestModel)	{
		String username = loginRequestModel.getUsername();
		String password = loginRequestModel.getPassword();
		UserDetails userDetails = userService.loadUserByUsername(username);
		String dbPassword = userDetails.getPassword();
		if(pwdEncoder.matches(password, dbPassword))	{
			String token = jwtUtil.generateToken(username);
			
			return ResponseEntity.ok().header("Authorization", "Bearer " + token)
					.body(Map.of("token", token, "username", username));
		} else {
	        // return 401 Unauthorized with an error message
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of("error", "Invalid username or password"));
	    }
		
	}
	
	/*@GetMapping("/status")
	public String checkStatus()	{
		return "checkStatus endpoint executed in LoginController";
	}*/
	
	@GetMapping("/getBalance")
	public long getBalance(@RequestHeader("username") String username)	{
		
		return accountFeign.currentBalance(getAccountNoFromUsername(username));
	}
	
	@GetMapping("/getStatement")
	public List<Transaction> getStatement(@RequestHeader("username") String username)	{
		LocalDate currentDate = LocalDate.now();
		LocalDate last7days = currentDate.minusDays(7);
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
		String startDate = dateFormatter.format(last7days);
		String endDate = dateFormatter.format(currentDate);
		String loggedInUserAccountNo = getAccountNoFromUsername(username);
		return accountFeign.transactionHistory(loggedInUserAccountNo, startDate, endDate);
	}
	
	@GetMapping("/loggedInUserAccountNo")
	public String getLoggedInUserAccountNo(@RequestHeader("username") String username)	{
		return getAccountNoFromUsername(username);
	}

	private String getAccountNoFromUsername(String username) {
		return userService.findUserByEmail(username).getAccount().getAccountNumber();
	}
	
}
