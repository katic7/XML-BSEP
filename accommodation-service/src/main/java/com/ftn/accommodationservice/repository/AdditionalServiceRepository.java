package com.ftn.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.accommodationservice.model.AdditionalService;

@Repository
public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Long> {

}
