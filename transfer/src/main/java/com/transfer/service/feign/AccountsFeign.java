/**
 * 
 */
package com.transfer.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.transfer.service.model.DepositWithdrawRequestModel;


/**
 * 
 */
@FeignClient(name="accounts-service", configuration = FeignClientConfig.class)
public interface AccountsFeign {

	@GetMapping("/account/currentBalance/{accountNo}")
	public long currentBalance(@PathVariable String accountNo);
	
	@PostMapping("/account/withdraw")
	public String withdraw(@RequestBody DepositWithdrawRequestModel model);
	
	@PostMapping("/account/deposit")
	public String deposit(@RequestBody DepositWithdrawRequestModel model);
}
