package com.ftn.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.agentservice.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
