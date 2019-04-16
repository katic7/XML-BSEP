package com.ftn.accommodationservice.service;

import java.util.List;


import com.ftn.accommodationservice.model.Address;

public interface AddressService {

	List<Address> getAddress();

	Address addAddress(Address address);
}
