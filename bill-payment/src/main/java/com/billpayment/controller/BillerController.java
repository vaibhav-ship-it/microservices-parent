/**
 * 
 */
package com.billpayment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.billpayment.entity.Biller;
import com.billpayment.feign.AccountFeign;
import com.billpayment.feign.LoginFeign;
import com.billpayment.model.DepositWithdrawRequestModel;
import com.billpayment.service.BillerService;

/**
 * 
 */
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class BillerController {

	private BillerService billerService;
	private AccountFeign accountFeign;
	private LoginFeign loginFeign;
	
	public BillerController(BillerService billerService, AccountFeign accountFeign,
			LoginFeign loginFeign)	{
		this.billerService = billerService;
		this.accountFeign = accountFeign;
		this.loginFeign = loginFeign;
	}
	
	@PostMapping("/biller")
	public Biller addBiller(@RequestBody Biller biller)	{
		return billerService.save(biller);
	}
	
	@DeleteMapping("/biller/{id}")
	public String deleteBillerById(@PathVariable int id)	{
		billerService.deleteById(id);
		return "Biller with Id " + id + " is Deleted";
	}
	
	@PostMapping("/billPayment/")
	public void billPayment(@RequestParam int billerId, @RequestHeader("username") String username)	{
		Optional<Biller> billerOptional = billerService.findById(billerId);
		Biller biller = billerOptional.orElseThrow(()->new RuntimeException("Biller not found"));
		String loggedInUserAccountNo = loginFeign.getLoggedInUserAccountNo(username);
		DepositWithdrawRequestModel withdrawRequestModel = new DepositWithdrawRequestModel();
		withdrawRequestModel.setAccountNo(loggedInUserAccountNo);
		withdrawRequestModel.setAmount(biller.getAmount());
		withdrawRequestModel.setDescription("Bill Paid for Biller " + biller.getName() + " of Rs " + biller.getAmount());
		accountFeign.withdraw(withdrawRequestModel);
	}
	
	@GetMapping("/billers")
	public List<Biller> getAllBillers()	{
		return billerService.findAll();
	}
	
	@GetMapping("/biller/{id}")
	public Biller getBillerById(@PathVariable int id)	{
		return billerService.findById(id).orElseThrow(()->new RuntimeException("Biller not found"));
	}
}
