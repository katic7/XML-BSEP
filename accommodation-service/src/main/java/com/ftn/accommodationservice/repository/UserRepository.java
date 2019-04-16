package com.ftn.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.accommodationservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	//Optional<User> findByUsername(String username);
}
