/**
 * 
 */
package com.netbanking.accounts.service;

import java.util.Optional;

import com.netbanking.accounts.entity.Account;

/**
 * 
 */
public interface AccountService {

	public Account save(Account account);
	public Optional<Account> findByAccountNumber(String accountNumber);
}
