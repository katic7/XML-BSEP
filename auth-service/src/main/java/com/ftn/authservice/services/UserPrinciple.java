package com.ftn.authservice.services;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String id;
	private String password;
	private String username;
	private Collection<? extends GrantedAuthority> autorities;
	
	public UserPrinciple(String id, String password, String username, Collection<? extends GrantedAuthority> autorities) {
		this.id = id;
		this.password = password;
		this.username = username;
		this.autorities = autorities;

	}
	
	public UserPrinciple(String username,Collection<? extends GrantedAuthority> autorities ) {
		this(null,null,username,autorities);
	}
	
	public UserPrinciple(String id, String username, Collection<? extends GrantedAuthority> autorities) {
		this(id, null, username, autorities);
	}
		
	public String getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}