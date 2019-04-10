package com.ftn.authservice.dto;

import java.util.ArrayList;
import java.util.List;

import com.ftn.authservice.model.Role;
import com.ftn.authservice.model.User;

public class UserDTO {

	private Long id;
	private String name;
	private String username;
	private String email;
	private String password;
	private List<RoleDTO> rolesDTO;
	
	public UserDTO(User user) {
		
		this(user.getId(), user.getName(),user.getUsername(),user.getEmail(),user.getPassword());
		rolesDTO = new ArrayList<>();
		for(Role rol : user.getRoles()) {
			rolesDTO.add(new RoleDTO(rol.getName()));
		}
	}
	
	public UserDTO(Long id, String name, String username, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public List<RoleDTO> getRolesDTO() {
		return rolesDTO;
	}
	public void setRolesDTO(List<RoleDTO> rolesDTO) {
		this.rolesDTO = rolesDTO;
	}
	
	
	
}
