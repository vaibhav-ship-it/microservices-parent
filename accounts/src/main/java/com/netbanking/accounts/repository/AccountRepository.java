/**
 * 
 */
package com.netbanking.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netbanking.accounts.entity.Account;

/**
 * 
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	public Optional<Account> findByAccountNumber(String accountNumber);
}
