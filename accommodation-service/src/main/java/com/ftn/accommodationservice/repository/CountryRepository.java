package com.ftn.accommodationservice.repository;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.ftn.accomodation_service.model.Country;
import com.ftn.accomodation_service.model.Currency;

@Component
public class CountryRepository {
	
	private static final Map<String, Country> countries = new HashMap<>();
	 
    @PostConstruct
    public void initData() {
        // initialize countries map
    	Country con = new Country();
    	con.setName("SPANIJA");
    	con.setCapital("xsa");
    	con.setPopulation(600);
    	con.setCurrency(Currency.GBP);
    	countries.put("spe", con);
    }
 
    public Country findCountry(String name) {
        return countries.get(name);
    }
}
