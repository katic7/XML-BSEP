package com.ftn.accommodationservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.accommodationservice.model.AccUnitPrice;
import com.ftn.accommodationservice.model.AccommodationObject;
import com.ftn.accommodationservice.model.AccommodationUnit;
import com.ftn.accommodationservice.repository.AccommodationObjectRepository;
import com.ftn.accommodationservice.repository.AccommodationUnitPriceRepository;
import com.ftn.accommodationservice.repository.AccommodationUnitRepository;

@Service
public class AccommodationObjectServiceImpl implements AccommodationObjectService {
	
	@Autowired
	private AccommodationUnitRepository acurepo;
	
	@Autowired
	private AccommodationUnitPriceRepository acuprepo;
	
	@Autowired
	private AccommodationObjectRepository acorepo;

	@Override
	public AccUnitPrice addNewPrice(AccUnitPrice acup) {
		return acuprepo.save(acup);
	}
	
	@Override
	public List<AccUnitPrice> getAllPrices() {
		return acuprepo.findAll();
	}

	@Override
	public AccommodationUnit AddNewAccUnit(AccommodationUnit acu) {
		return acurepo.save(acu);
	}

	@Override
	public AccommodationObject getOneAccObj(Long id) {
		return acorepo.getOne(id);
	}

	@Override
	public AccommodationUnit getOneAccUnit(Long id) {
		return acurepo.getOne(id);
	}

	@Override
	public List<AccommodationUnit> getUnits() {
		return acurepo.findAll();
	}

	
	
	

}
