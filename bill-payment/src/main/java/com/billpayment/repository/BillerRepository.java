/**
 * 
 */
package com.billpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billpayment.entity.Biller;

/**
 * 
 */
public interface BillerRepository extends JpaRepository<Biller, Integer> {

}
