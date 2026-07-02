/**
 * 
 */
package com.statement.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 
 */
@FeignClient("login-service")
public interface LoginFeign {

	@GetMapping("/loggedInUserAccountNo")
	public String getLoggedInUserAccountNo(@RequestHeader("username") String username);
}
