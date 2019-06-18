package com.ftn.agentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.agentservice.model.AccommodationObject;


@Repository
public interface AccommodationObjectRepository extends JpaRepository<AccommodationObject, Long>{
	
}
