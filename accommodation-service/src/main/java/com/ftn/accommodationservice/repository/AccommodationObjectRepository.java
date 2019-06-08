package com.ftn.accommodationservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.accommodationservice.model.AccommodationObject;


@Repository
public interface AccommodationObjectRepository extends JpaRepository<AccommodationObject, Long>{
	
	@Modifying
	@Query(value = "SELECT * FROM megatravel.accommodation_object, megatravel.agents where acc_obj_id = megatravel.accommodation_object.id;", nativeQuery = true)
	public List<AccommodationObject> objWithAgents();
}
