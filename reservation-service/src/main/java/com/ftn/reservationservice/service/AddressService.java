package com.ftn.reservationservice.service;

import java.util.List;

import com.ftn.reservationservice.model.Address;

public interface AddressService {
	
	List<Address> getAllAdresses();
	Address getOne(Long id);
}
