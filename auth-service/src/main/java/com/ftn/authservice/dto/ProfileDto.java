package com.ftn.authservice.dto;

import java.util.List;

public class ProfileDto {

	private String username;
	
	private List<String> authorities;
	
	private boolean nonLocked;
	
	private ProfileDto() {
		
	}
	
	public ProfileDto(String username, List<String> authorities, boolean nonLocked) {
		this.username = username;
		this.authorities = authorities;
		this.nonLocked = nonLocked;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<String> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	
	public void setNonLocked() {
		this.nonLocked = !nonLocked;
	}
	
	public boolean getNonLocked() {
		return this.nonLocked;
	}
	
}
