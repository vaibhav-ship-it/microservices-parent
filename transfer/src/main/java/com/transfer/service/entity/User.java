/**
 * 
 */
package com.transfer.service.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable	{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3178871484357535865L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, unique=true)
	private String userId;
	
	@Column(nullable=false, length=50)
	private String firstName;
	
	@Column(nullable=false, length=50)
	private String lastName;
	
	@Column(nullable=false, length=10, unique=true)
	private String mobile;
	
	@Column(nullable=false, length=120, unique=true)
	private String email;
	
	@Column(nullable=false, length=255)	
	private String address;
	
	@Column(nullable=false)
	private String encryptedPassword;
	
	/*@Column(nullable=false, unique=true)
	private String accountNo;*/
	
	@OneToOne
	@JoinColumn(name="account_id")
	private Account account;
	
}
