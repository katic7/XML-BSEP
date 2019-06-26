package com.ftn.reservationservice.dto;

import com.ftn.reservationservice.model.RoleName;

public class RoleDTO {
	private Long id;
	private RoleName name;
	
	public RoleDTO() {}

    public RoleDTO(RoleName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
