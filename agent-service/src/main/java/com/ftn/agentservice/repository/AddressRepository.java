package com.ftn.agentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.agentservice.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
