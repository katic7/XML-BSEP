package com.ftn.agentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.agentservice.model.AccommodationUnit;


@Repository
public interface AccommodationUnitRepository extends JpaRepository<AccommodationUnit, Long> {
	
	@Query(value="select * from accommodation_unit ac where accommodation_object = ?1",nativeQuery = true)
	List<AccommodationUnit> findAllByObject(Long id);
}
