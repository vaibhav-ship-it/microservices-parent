/**
 * 
 */
package com.netbanking.accounts.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
public class DepositWithdrawRequestModel {

	private String accountNo;
	private long amount;
	private String description;
	//private String transactionType;
}
