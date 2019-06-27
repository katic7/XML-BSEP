package com.ftn.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.agentservice.model.AdditionalService;



@Repository
public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Long> {

	
	AdditionalService findByName(String name);
}
