package com.ftn.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.accommodationservice.model.AccommodationObject;

@Repository
public interface AccommodationObjectRepository extends JpaRepository<AccommodationObject, Long>{

}
