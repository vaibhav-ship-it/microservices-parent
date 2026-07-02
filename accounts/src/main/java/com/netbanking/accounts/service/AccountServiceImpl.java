/**
 * 
 */
package com.netbanking.accounts.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.netbanking.accounts.entity.Account;
import com.netbanking.accounts.repository.AccountRepository;

/**
 * 
 */
@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}
	public Optional<Account> findByAccountNumber(String accountNumber)	{
		return accountRepository.findByAccountNumber(accountNumber);
	}
}
