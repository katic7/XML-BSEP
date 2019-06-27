package com.ftn.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.agentservice.model.AccUnitPrice;


@Repository
public interface AccommodationUnitPriceRepository extends JpaRepository<AccUnitPrice, Long> {

}
