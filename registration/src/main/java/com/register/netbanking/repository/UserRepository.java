/**
 * 
 */
package com.register.netbanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.register.netbanking.entity.User;

/**
 * 
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>	{

	public boolean existsByMobile(String mobile);
	public boolean existsByEmail(String email);
	
}
