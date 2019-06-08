package com.ftn.accommodationservice.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "users")
@NamedEntityGraph(name = "User.Roles.Permissions", 
	attributeNodes = @NamedAttributeNode(value = "roles", subgraph = "permissions"), 
	subgraphs = @NamedSubgraph(name = "permissions", attributeNodes = @NamedAttributeNode("permissions")))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	private String surname;
	
	protected Long addressId;	
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	private boolean nonLocked;
	
	protected Date dateOfBirth;
	
	protected String telephone;
	
	@OneToMany(mappedBy="user")
    private List<Reservation> reservations;
	

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public User() {
		this.enabled = false;
	}

	public User(String name, String surname, Long address, String email, 
				String password, Set<Role> roles) {
		super();
		this.name = name;
		this.surname = surname;
		this.addressId = address;
		this.email = email;
		this.password = password;
		this.enabled = true;
		this.nonLocked = true;
		this.roles = roles;
	}

	public User(Long id, String name, String surname, Long addressId, String email, String password,
			boolean enabled, boolean nonLocked, Date dateOfBirth, String telephone,
			List<com.ftn.accommodationservice.model.Reservation> reservations, Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.addressId = addressId;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.nonLocked = nonLocked;
		this.dateOfBirth = dateOfBirth;
		this.telephone = telephone;
		this.reservations = reservations;
		this.roles = roles;
	}

	public User(String email, String password, Set<Role> roles) {
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.enabled = true;
		this.nonLocked = true;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

}
