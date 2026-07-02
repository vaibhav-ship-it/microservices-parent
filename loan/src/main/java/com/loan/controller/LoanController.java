/**
 * 
 */
package com.loan.controller;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.loan.entity.Loan;
import com.loan.feign.AccountsFeign;
import com.loan.feign.LoginFeign;
import com.loan.model.DepositWithdrawRequestModel;
import com.loan.model.LoanRequestModel;
import com.loan.service.LoanService;

import jakarta.validation.Valid;

/**
 * 
 */
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class LoanController {

	private LoanService loanService;
	private AccountsFeign accountsFeign;
	private LoginFeign loginFeign;
	
	public LoanController(LoanService loanService, AccountsFeign accountsFeign,
			LoginFeign loginFeign) {
		this.loanService = loanService;
		this.accountsFeign = accountsFeign;
		this.loginFeign = loginFeign;
	}

	@PostMapping("/loan")
	public ResponseEntity<String> processLoan(@Valid @RequestBody LoanRequestModel loanRequestModel,
			@RequestHeader("username") String username, BindingResult bindingResult)	{
		if(bindingResult.hasErrors())	{
			String errors = bindingResult.getAllErrors().stream().map(oe->oe.getDefaultMessage()).collect(Collectors.joining(", "));
			throw new RuntimeException(errors);
		}
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Loan loan = modelMapper.map(loanRequestModel, Loan.class);
		Loan savedLoan = loanService.save(loan);
		if(savedLoan != null)	{
			DepositWithdrawRequestModel depositRequestModel = new DepositWithdrawRequestModel();
			depositRequestModel.setAccountNo(loginFeign.getLoggedInUserAccountNo(username));
			depositRequestModel.setAmount(savedLoan.getAmount());
			depositRequestModel.setDescription("Loan amount of Rs " + savedLoan.getAmount() + " is deposited");
			accountsFeign.deposit(depositRequestModel);
			return ResponseEntity.ok("{\"message\": \"Loan Approved !!!\"}");
		} else	{
			return ResponseEntity.internalServerError().body("{\"message\": \"Error processing Loan\"}");
		}
	}
}
