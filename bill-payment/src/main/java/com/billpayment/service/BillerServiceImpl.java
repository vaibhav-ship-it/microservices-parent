/**
 * 
 */
package com.billpayment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.billpayment.entity.Biller;
import com.billpayment.repository.BillerRepository;

/**
 * 
 */
@Service
public class BillerServiceImpl implements BillerService {

	private BillerRepository billerRepository;
	
	@Override
	public Biller save(Biller biller) {
		return billerRepository.save(biller);
	}

	@Override
	public void delete(Biller biller) {
		billerRepository.delete(biller);
	}

	@Override
	public void deleteById(int id)	{
		billerRepository.deleteById(id);
	}
	
	@Override
	public Optional<Biller> findById(int id)	{
		return billerRepository.findById(id);
	}
	
	@Override
	public List<Biller> findAll()	{
		return billerRepository.findAll();
	}
}
