package com.ftn.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.accommodationservice.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
