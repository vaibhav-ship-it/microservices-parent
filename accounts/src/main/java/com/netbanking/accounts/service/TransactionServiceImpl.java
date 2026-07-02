/**
 * 
 */
package com.netbanking.accounts.service;

import org.springframework.stereotype.Service;

import com.netbanking.accounts.entity.Transaction;
import com.netbanking.accounts.repository.TransactionRepository;

/**
 * 
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	private TransactionRepository transactionRepository;
	
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public Transaction save(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

}
