package com.ftn.agentservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ftn.agentservice.dto.RoleDTO;
import com.ftn.agentservice.dto.UserDTO;


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
	
	public User(UserDTO u) {
		Set<Role> role = new HashSet<Role>();
		Set<Permission> per = new HashSet<Permission>();
		if(u.getRoles() != null) {
			for(RoleDTO r : u.getRoles()) {
			Role ro = new Role();
			ro.setName(r.getName());
			ro.setId(r.getId());
			ro.setPermissions(per);
			role.add(ro);
		}
		}
		this.id = u.getId();
		this.name = u.getName();
		this.surname = u.getSurname();
		this.addressId = u.getAddressId();
		this.email = u.getEmail();
		this.password = u.getPassword();
		this.enabled = u.isEnabled();
		this.nonLocked = u.isNonLocked();
		this.dateOfBirth = new Date();
		this.telephone = u.getTelephone();
		this.reservations = new ArrayList<Reservation>();
		this.roles = role;
	}

	public User(Long id, String name, String surname, Long addressId, String email, String password,
			boolean enabled, boolean nonLocked, Date dateOfBirth, String telephone,
			List<com.ftn.agentservice.model.Reservation> reservations, Set<Role> roles) {
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
