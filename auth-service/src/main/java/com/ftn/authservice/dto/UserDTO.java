package com.ftn.authservice.dto;

import com.ftn.authservice.model.User;

public class UserDTO {

	private Long id;
	private String name;
	private String surname;
	private String address;
	private int postalCode;
	private String email;
	private String password;
	private boolean enabled;
	private boolean nonLocked;
	
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(Long id, String name, String surname, String address, int postalCode, String email, String password,
			boolean enabled, boolean nonLocked) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.postalCode = postalCode;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.nonLocked = nonLocked;
	}
	
	public UserDTO(User u) {
		this.id = u.getId();
		this.name = u.getName();
		this.surname = u.getSurname();
		this.address = u.getAddress();
		this.postalCode = u.getPostalCode();
		this.email = u.getEmail();
		this.password = u.getPassword();
		this.enabled = u.isEnabled();
		this.nonLocked = u.isNonLocked();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isNonLocked() {
		return nonLocked;
	}

	public void setNonLocked(boolean nonLocked) {
		this.nonLocked = nonLocked;
	}
	
	
}
