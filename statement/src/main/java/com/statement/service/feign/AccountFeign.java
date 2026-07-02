/**
 * 
 */
package com.statement.service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.statement.service.entity.Transaction;

/**
 * 
 */
@FeignClient(name="accounts-service")
public interface AccountFeign {

	@GetMapping("/account/transactionHistory/{accountNo}/{startDate}/{endDate}")
	public List<Transaction> transactionHistory(@PathVariable String accountNo, @PathVariable String startDate, @PathVariable String endDate);

}
