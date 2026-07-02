/**
 * 
 */
package com.login.netbanking.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Transaction implements Comparable<Transaction>	{

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private LocalDateTime transactionTime;
	
	@Column
	private String refNo;
	
	@Column
	private String description;
	
	@Column
	private long amount;
	
	@Column
	private TransactionType transactionType;
	
	@Column
	private long balance;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	@JsonIgnore
	private Account account;

	@Override
	public int compareTo(Transaction t) {
		return this.getTransactionTime().compareTo(t.getTransactionTime());
	}
	
}
