package com.ftn.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.authservice.model.Role;
import com.ftn.authservice.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(RoleName name);
	
	boolean existsByName(RoleName name);
	
}
