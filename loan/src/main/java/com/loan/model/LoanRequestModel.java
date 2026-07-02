/**
 * 
 */
package com.loan.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
public class LoanRequestModel {

	@Min(value=100000, message="{loan.minimum.amount}")
	private long amount;
	
	@NotBlank(message="{loan.blank.purpose}")
	@Size(min = 10, max = 255, message="{loan.size.purpose}")
	private String purpose;
}
