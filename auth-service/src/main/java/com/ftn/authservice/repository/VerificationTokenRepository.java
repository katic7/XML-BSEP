package com.ftn.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.authservice.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {  
	VerificationToken findByConfirmationToken(String confirmationToken);
}
