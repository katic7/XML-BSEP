package com.ftn.reservationservice.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.reservationservice.model.Reservation;

@Repository
public interface ReservationServiceRepository extends JpaRepository<Reservation, Long> {
	
	@Modifying	
	@Query(value = "select distinct r.id from reservation r, accommodation_object ao, accommodation_unit au, address adr where"
			+ " lower(adr.town) like lower(?1) and adr.id = ao.address_id and ao.id = r.accommodation_unit and not((r.begin_date between ?2 and ?3) or (r.end_date between ?2 and ?3))",nativeQuery = true)	
	List<BigInteger> findReservationsInInterval(String dest, Date startDate, Date endDate);

}
