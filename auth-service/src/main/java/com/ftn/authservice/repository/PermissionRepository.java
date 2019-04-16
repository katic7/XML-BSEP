package com.ftn.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.authservice.model.Permission;


public interface PermissionRepository extends JpaRepository<Permission, Long> {
	 Optional<Permission> findByName(String name);
}
