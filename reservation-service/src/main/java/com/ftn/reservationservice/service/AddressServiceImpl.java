package com.ftn.reservationservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.reservationservice.model.Address;
import com.ftn.reservationservice.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressRepository addressRepository;

	@Override
	public List<Address> getAllAdresses() {
		return addressRepository.findAll();
	}

	@Override
	public Address getOne(Long id) {
		return addressRepository.getOne(id);
	}

}
