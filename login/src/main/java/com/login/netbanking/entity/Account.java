/**
 * 
 */
package com.login.netbanking.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Entity
@Table
@Getter
@Setter
public class Account {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, unique=true)
	private String accountNumber;
	
	@Column(nullable=false)
	private long balance;
	
	@OneToMany
	private List<Transaction> transactions;
	
	@OneToOne(mappedBy="account")
	private User user;
	
}
