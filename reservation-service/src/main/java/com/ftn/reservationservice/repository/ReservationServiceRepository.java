package com.ftn.reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.reservationservice.model.Address;

@Repository
public interface ReservationServiceRepository extends JpaRepository<Address, Long> {
	
	

}
