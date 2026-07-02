/**
 * 
 */
package com.statement.service.entity;

import java.util.TreeSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	private TreeSet<Transaction> transactions = new TreeSet<>();
	
}
