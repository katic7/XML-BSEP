package com.ftn.accommodationservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.accommodationservice.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
}
