package com.ftn.accommodationservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.accommodationservice.dto.AccommodationObjectDTO;
import com.ftn.accommodationservice.model.AccUnitPrice;
import com.ftn.accommodationservice.model.AccommodationObject;
import com.ftn.accommodationservice.model.AccommodationUnit;
import com.ftn.accommodationservice.model.AdditionalService;
import com.ftn.accommodationservice.model.Category;
import com.ftn.accommodationservice.model.Reservation;
import com.ftn.accommodationservice.model.Type;
import com.ftn.accommodationservice.repository.AccommodationObjectRepository;
import com.ftn.accommodationservice.repository.AdditionalServiceRepository;
import com.ftn.accommodationservice.repository.CategoryRepository;
import com.ftn.accommodationservice.repository.TypeRepository;
import com.ftn.accommodationservice.service.AccommodationObjectService;

@RestController
@RequestMapping("/api/accobject")
public class AccommodationObjectController {
	
	@Autowired
	private AccommodationObjectService accommodationObjectService;
	
	@Autowired
	private AccommodationObjectRepository accommodationObjectRepository;
	
	@Autowired
	private AdditionalServiceRepository additonalRepo;
	
	@Autowired
	private CategoryRepository catrepo;
	
	@Autowired
	private TypeRepository typerepo;
	
	@PreAuthorize("hasAuthority('AddPrice')")
	@GetMapping("/getprices")
	public ResponseEntity<List<AccUnitPrice>> getAllPrices() {
		List<AccUnitPrice> prices = accommodationObjectService.getAllPrices();
		if(prices != null) {
			System.out.println("dosao");
			return new ResponseEntity<List<AccUnitPrice>>(prices, HttpStatus.OK);
		} 

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PreAuthorize("hasAuthority('AddPrice')")
	@PostMapping("/addprice")
	public ResponseEntity<AccUnitPrice> addNewPrice(@RequestBody AccUnitPrice acup) {
		AccUnitPrice price = new AccUnitPrice();
		if(acup != null) {
			price.setStartDate(acup.getStartDate());
			price.setEndDate(acup.getEndDate());
			price.setPrice(acup.getPrice());
			
			//price.setAccommodationUnit(new AccommodationUnit());
			
			accommodationObjectService.addNewPrice(price);
			
			return new ResponseEntity<AccUnitPrice>(price, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getOne/{id}")
	public AccommodationObjectDTO getObj(@PathVariable Long id){
		AccommodationObject acc = accommodationObjectService.getOneAccObj(id);
		AccommodationObjectDTO accDto = new AccommodationObjectDTO(acc.getId(),acc.getName(), acc.getAddress().getId(), acc.getDescription(), acc.getCategory().getId(), acc.isFreeCancelation(), acc.getDaysToCancel(), acc.getType().getId());
		return accDto;
	}
	
	@GetMapping("/getAllwOutAgent")
	public List<AccommodationObjectDTO> getFreeAcc(){
		List<AccommodationObject> svi = accommodationObjectRepository.findAll();
		List<AccommodationObject> lista= accommodationObjectRepository.objWithAgents();
		List<AccommodationObjectDTO> povratnaLista = new ArrayList<AccommodationObjectDTO>();
		for(AccommodationObject acc : lista) {
			svi.remove(acc);
		}
		for(AccommodationObject acc : svi) {
			povratnaLista.add(new AccommodationObjectDTO(acc.getId(),acc.getName(), acc.getAddress().getId(), acc.getDescription(), acc.getCategory().getId(), acc.isFreeCancelation(), acc.getDaysToCancel(), acc.getType().getId()));
		}
		
		
	return povratnaLista;
	}
	
	@PreAuthorize("hasAuthority('AddAccUnit')")
	@PostMapping("/addunit/{accobject_id}")
	public ResponseEntity<AccommodationUnit> addNewAccUnit(@PathVariable Long accobject_id, @RequestBody AccommodationUnit acu) {
		AccommodationUnit newAcu = new AccommodationUnit();
		if(acu != null) {
			AccommodationObject aco = accommodationObjectService.getOneAccObj(accobject_id);
			newAcu.setAccommodationObject(aco);
			
			newAcu.setAdditionalServices(acu.getAdditionalServices());
			newAcu.setBalcony(acu.isBalcony());
			newAcu.setDescription(acu.getDescription());
			newAcu.setNumberOfBeds(acu.getNumberOfBeds());
			newAcu.setReservations(new ArrayList<Reservation>());
			newAcu.setPrice(acu.getPrice());
			newAcu.setRating(0);
			
			accommodationObjectService.AddNewAccUnit(newAcu);
			
			return new ResponseEntity<AccommodationUnit>(newAcu, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getOneUnit/{id}")
	public ResponseEntity<AccommodationUnit> getOneAccUnit(@PathVariable Long id) {
		AccommodationUnit acu = accommodationObjectService.getOneAccUnit(id);
		if(acu != null) {
			return new ResponseEntity<AccommodationUnit>(acu, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PreAuthorize("hasAuthority('GetAdditionalServices')")
	@GetMapping("/additionalservices/{name}")
	public ResponseEntity<AdditionalService> getService(@PathVariable String name) {
		AdditionalService add = additonalRepo.findByName(name);
		if(add != null) {
			System.out.println(add.getName());
			return new ResponseEntity<AdditionalService>(add, HttpStatus.OK);
		} 
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getUnits")
	public ResponseEntity<List<AccommodationUnit>> getUnits() {
		List<AccommodationUnit> acus = accommodationObjectService.getUnits();
		if(acus != null) {
			return new ResponseEntity<List<AccommodationUnit>>(acus, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	/* 
	 * 
	 * 	SIFRANIK
	 * 
	 * */
	
	@GetMapping("/types")
	public ResponseEntity<List<Type>> getAllTypes() {
		List<Type> ty = new ArrayList<>();
		ty = typerepo.findAll();
		return new ResponseEntity<List<Type>>(ty, HttpStatus.OK);
	}
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> ty = new ArrayList<>();
		ty = catrepo.findAll();
		return new ResponseEntity<List<Category>>(ty, HttpStatus.OK);
	}
	
	@GetMapping("/additionalservices")
	public ResponseEntity<List<AdditionalService>> getAllAdditionalServices() {
		List<AdditionalService> ty = new ArrayList<>();
		ty = additonalRepo.findAll();
		return new ResponseEntity<List<AdditionalService>>(ty, HttpStatus.OK);
	}
	
	@GetMapping("/types/{id}")
	public ResponseEntity<Type> getOneType(@PathVariable Long id) {
		Type ty = new Type();
		ty = typerepo.getOne(id);
		return new ResponseEntity<Type>(ty, HttpStatus.OK);
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<Category> getOneCategory(@PathVariable Long id) {
		Category ty = new Category();
		ty = catrepo.getOne(id);
		return new ResponseEntity<Category>(ty, HttpStatus.OK);
	}
	
	@GetMapping("/additionalservices/{id}")
	public ResponseEntity<AdditionalService> getOneAdditionalService(@PathVariable Long id) {
		AdditionalService ty = new AdditionalService();
		ty = additonalRepo.getOne(id);
		return new ResponseEntity<AdditionalService>(ty, HttpStatus.OK);
	}
	
	@DeleteMapping("/types/{id}")
	public ResponseEntity<?> deleteOneType(@PathVariable Long id) {		
		typerepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<?> deleteOneCategory(@PathVariable Long id) {
		catrepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/additionalservices/{id}")
	public ResponseEntity<?> deleteOneAdditionalService(@PathVariable Long id) {
		additonalRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/types")
	public ResponseEntity<Type> addType(@RequestBody Type type) {
		Type ty = new Type();
		ty.setAccObj(new ArrayList<AccommodationObject>());
		ty.setName(type.getName());
		typerepo.save(ty);
		return new ResponseEntity<Type>(ty, HttpStatus.OK);
	}
	
	@PostMapping("/categories")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		Category cat = new Category();
		cat.setAccObj(new ArrayList<AccommodationObject>());
		cat.setName(category.getName());
		catrepo.save(cat);
		return new ResponseEntity<Category>(cat, HttpStatus.OK);
	}
	
	@PostMapping("/additionalservices")
	public ResponseEntity<AdditionalService> addAdditionalService(@RequestBody AdditionalService addser) {
		AdditionalService as = new AdditionalService();
		as.setAccommodationUnits(new ArrayList<AccommodationUnit>());
		as.setIncluded(addser.isIncluded());
		as.setPrice(addser.getPrice());
		additonalRepo.save(as);
		return new ResponseEntity<AdditionalService>(as, HttpStatus.OK);
	}
	
	@PostMapping("/types/{id}")
	public ResponseEntity<Type> updateType(@PathVariable Long id, @RequestBody Type type) {
		Type ty = typerepo.getOne(type.getId());
		ty.setName(type.getName());
		typerepo.save(ty);
		return new ResponseEntity<Type>(ty, HttpStatus.OK);
	}
	
	@PostMapping("/categories/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
		Category cat = catrepo.getOne(category.getId());
		cat.setName(category.getName());
		catrepo.save(cat);
		return new ResponseEntity<Category>(cat, HttpStatus.OK);
	}
	
	@PostMapping("/additionalservices/{id}")
	public ResponseEntity<AdditionalService> updateAdditionalService(@PathVariable Long id, @RequestBody AdditionalService addser) {
		AdditionalService as = additonalRepo.getOne(addser.getId());
		as.setName(addser.getName());
		as.setPrice(addser.getPrice());
		additonalRepo.save(as);
		return new ResponseEntity<AdditionalService>(as, HttpStatus.OK);
	}
	
	

}