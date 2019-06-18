package com.ftn.agentservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.agentservice.model.User;



public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
