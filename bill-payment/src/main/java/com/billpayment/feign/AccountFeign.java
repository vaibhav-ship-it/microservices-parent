/**
 * 
 */
package com.billpayment.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.billpayment.model.DepositWithdrawRequestModel;

/**
 * 
 */
@FeignClient(name="accounts-service")
public interface AccountFeign {

	@PostMapping("/account/withdraw")
	public String withdraw(@RequestBody DepositWithdrawRequestModel model);

}
