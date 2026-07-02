/**
 * 
 */
package com.loan.service;

import org.springframework.stereotype.Service;

import com.loan.entity.Loan;
import com.loan.repository.LoanRepository;

/**
 * 
 */
@Service
public class LoanServiceImpl implements LoanService {
	
	private LoanRepository loanRepository;

	public LoanServiceImpl(LoanRepository loanRepository) {
		this.loanRepository = loanRepository;
	}

	@Override
	public Loan save(Loan loan) {
		return loanRepository.save(loan);
	}

}
