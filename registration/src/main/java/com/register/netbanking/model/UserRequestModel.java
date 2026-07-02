/**
 * 
 */
package com.register.netbanking.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
public class UserRequestModel {

	@NotNull
	@Size(min=3, max=50, message="{error.firstname.size.message}")
	@Pattern(regexp="[a-zA-Z\\s]{3,50}", message="{error.firstname.regexp.message}")
	private String firstName;

	@NotNull
	@Size(min=3, max=50, message="{error.lastname.size.message}")
	@Pattern(regexp="[a-zA-Z\\s]{3,50}", message="{error.lastname.regexp.message}")
	private String lastName;
	
	@NotNull
	@Pattern(regexp="[0-9]{10}", message="{error.mobile.regexp.message}")
	private String mobile;
	
	@NotNull
	@Size(min=10, max=100, message="{error.email.size.message}")
	@Email(message="{error.email.email.message}")
	private String email;
	
	@NotNull
	@Size(min=10, max=255, message="{error.address.size.message}")
	private String address;
	
	@NotNull
	@Size(min=8, max=16, message="{error.password.size.message}")
	@Pattern(regexp="[a-zA-Z0-9!@#$]{8,16}", message="{error.password.regexp.message}")
	private String password;
}
