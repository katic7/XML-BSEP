package com.ftn.authservice.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {

	@NotBlank
	private String name;
	
	@NotBlank
	private String surname;
	
	@NotBlank
	private String address;
	
	private int postalCode;
	
	@Email
	@NotBlank
	@Size(min = 3)
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String confirmPassword;
	
	public SignUpRequest() {
		super();
	}

	public SignUpRequest(@NotBlank String name, @NotBlank String surname, @NotBlank String address,
			@NotBlank int postalCode, @NotBlank @Size(min = 3) String email, @NotBlank String password,
			@NotBlank String confirmPassword) {
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.postalCode = postalCode;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
}
