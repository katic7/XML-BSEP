package com.ftn.authservice.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
@NamedEntityGraph(name = "User.Roles.Permissions", 
	attributeNodes = @NamedAttributeNode(value = "roles", subgraph = "permissions"), 
	subgraphs = @NamedSubgraph(name = "permissions", attributeNodes = @NamedAttributeNode("permissions")))
public class User {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String id;

	@Column(unique = true)
	private String email;
	private String password;

	@Column(name = "enabled")
	private boolean enabled;
	
	private boolean nonLocked;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public User() {
		this.enabled = false;
	}

	public User(String email, String password, Set<Role> roles) {
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.enabled = true;
		this.nonLocked = true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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
	
	public void setNonLocked(boolean locked) {
		this.nonLocked = locked;
	}

}
