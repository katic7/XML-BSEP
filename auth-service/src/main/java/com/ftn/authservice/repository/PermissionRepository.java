package com.ftn.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.authservice.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

	Permission findByName(String name);

	boolean existsByName(String name);

}
