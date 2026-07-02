/**
 * 
 */
package com.billpayment.service;

import java.util.List;
import java.util.Optional;

import com.billpayment.entity.Biller;

/**
 * 
 */
public interface BillerService {

	public Biller save(Biller biller);
	public void deleteById(int id);
	public void delete(Biller biller);
	public Optional<Biller> findById(int id);
	public List<Biller> findAll();
	
}
