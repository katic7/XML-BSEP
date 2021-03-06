package com.ftn.accommodationservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.accommodationservice.model.Address;
import com.ftn.accommodationservice.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;
	
	@Override
	public List<Address> getAddress() {
		return addressRepo.findAll();
	}

	@Override
	public Address addAddress(Address address) {
		return addressRepo.save(address);
	}

	@Override
	public Address getOne(Long id) {
		// TODO Auto-generated method stub
		return addressRepo.getOne(id);
	}

}
