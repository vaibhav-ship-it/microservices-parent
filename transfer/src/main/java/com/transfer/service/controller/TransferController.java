/**
 * 
 */
package com.transfer.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.transfer.service.feign.AccountsFeign;
import com.transfer.service.feign.LoginFeign;
import com.transfer.service.model.DepositWithdrawRequestModel;
import com.transfer.service.model.TransferRequestModel;

/**
 * 
 */
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class TransferController {

	private AccountsFeign accountsFeign;
	private LoginFeign loginFeign;
	
	public TransferController(AccountsFeign accountsFeign, LoginFeign loginFeign)	{
		this.accountsFeign = accountsFeign;
		this.loginFeign = loginFeign;
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<String> transfer(@RequestBody TransferRequestModel transferRequestModel, @RequestHeader("username") String username)	{
		// deposit in recipient account
		DepositWithdrawRequestModel depositRequestModel = new DepositWithdrawRequestModel();
		depositRequestModel.setAccountNo(transferRequestModel.getRecipientAccountNo());
		depositRequestModel.setAmount(transferRequestModel.getAmount());
		depositRequestModel.setDescription(transferRequestModel.getDescription());
		// withdraw from logged in user account
		String loggedInUserAccountNo = loginFeign.getLoggedInUserAccountNo(username);
		DepositWithdrawRequestModel withdrawRequestModel = new DepositWithdrawRequestModel();
		withdrawRequestModel.setAccountNo(loggedInUserAccountNo);
		withdrawRequestModel.setAmount(transferRequestModel.getAmount());
		withdrawRequestModel.setDescription(transferRequestModel.getDescription());
		accountsFeign.withdraw(withdrawRequestModel);
		accountsFeign.deposit(depositRequestModel);
		//String message = transferRequestModel.getAmount() +  " transferred to " + transferRequestModel.getRecipientAccountNo();
		return ResponseEntity.ok("{\"message\": \"Transferred Successfully !!!\"}");
		
	}
}
