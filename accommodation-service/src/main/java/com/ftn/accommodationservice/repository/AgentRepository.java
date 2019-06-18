package com.ftn.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.accommodationservice.model.Agent;



@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
	
	@Transactional
	@Modifying
	@Query(value = "insert into agents (pib, id, acc_obj_id) values (?1, ?2, ?3)", nativeQuery = true)
	void saveAgent(String str, Long id, Long accObj);

}