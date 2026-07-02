/**
 * 
 */
package com.register.netbanking.service;

import org.springframework.stereotype.Service;

import com.register.netbanking.entity.Account;
import com.register.netbanking.repository.AccountRepository;

/**
 * 
 */
@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository)	{
		this.accountRepository = accountRepository;
	}
	
	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

}
