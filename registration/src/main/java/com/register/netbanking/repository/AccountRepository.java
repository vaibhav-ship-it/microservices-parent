/**
 * 
 */
package com.register.netbanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.register.netbanking.entity.Account;

/**
 * 
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
