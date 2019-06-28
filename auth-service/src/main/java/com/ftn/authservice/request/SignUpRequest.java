package com.ftn.authservice.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ftn.authservice.validator.ValidPassword;

public class SignUpRequest {

	@Email
	@NotBlank
	@Size(min = 3)
	private String email;

	@NotBlank
	@ValidPassword
	private String password;

	@NotBlank
	private String confirmPassword;
	
	public SignUpRequest() {
		super();
	}
	
	public SignUpRequest(@NotBlank @Size(min = 3) String email, @NotBlank String password,
			@NotBlank String confirmPassword) {
		super();
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String username) {
		this.email = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

}
