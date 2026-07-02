/**
 * 
 */
package com.register.netbanking.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4826433594323517521L;

	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String mobile;
	
	private String email;
	
	private String address;
	
	private String password;
	
	private String encryptedPassword;
	
	//private String accountNo;
	
}
