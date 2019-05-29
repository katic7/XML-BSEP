package com.ftn.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.accommodationservice.model.AccUnitPrice;

@Repository
public interface AccommodationUnitPriceRepository extends JpaRepository<AccUnitPrice, Long> {

}