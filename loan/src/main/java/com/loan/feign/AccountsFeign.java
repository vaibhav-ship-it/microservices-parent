/**
 * 
 */
package com.loan.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.loan.model.DepositWithdrawRequestModel;

/**
 * 
 */
@FeignClient(name="accounts-service")
public interface AccountsFeign {
	
	@PostMapping("/account/deposit")
	public String deposit(@RequestBody DepositWithdrawRequestModel model);
}
