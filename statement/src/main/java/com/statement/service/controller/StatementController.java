/**
 * 
 */
package com.statement.service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.statement.service.entity.Transaction;
import com.statement.service.feign.AccountFeign;
import com.statement.service.feign.LoginFeign;

/**
 * 
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StatementController {

	private AccountFeign accountFeign;
	private LoginFeign loginFeign;
	
	public StatementController(AccountFeign accountFeign, LoginFeign loginFeign)	{
		this.accountFeign = accountFeign;
		this.loginFeign = loginFeign;
	}
	
	@GetMapping("/statement/{startDate}/{endDate}")
	public List<Transaction> getStatement(@PathVariable String startDate, @PathVariable String endDate, 
			@RequestHeader("username") String username)	{
		String loggedInUserAccountNo = loginFeign.getLoggedInUserAccountNo(username);
		return accountFeign.transactionHistory(loggedInUserAccountNo, startDate, endDate);
	}
	
	
}
