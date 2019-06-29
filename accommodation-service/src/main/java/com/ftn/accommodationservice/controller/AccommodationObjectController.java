package com.ftn.accommodationservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.ftn.accommodationservice.model.User;
import com.ftn.accommodationservice.repository.AccommodationObjectRepository;
import com.ftn.accommodationservice.repository.AdditionalServiceRepository;
import com.ftn.accommodationservice.repository.CategoryRepository;
import com.ftn.accommodationservice.repository.TypeRepository;
import com.ftn.accommodationservice.repository.UserRepository;
import com.ftn.accommodationservice.service.AccommodationObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	@Autowired 
	private UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(AccommodationObjectController.class);
	
	@PreAuthorize("hasAuthority('AddPrice')")
	@GetMapping("/getprices")
	public ResponseEntity<List<AccUnitPrice>> getAllPrices() {
		List<AccUnitPrice> prices = accommodationObjectService.getAllPrices();
		if(prices != null) {
			return new ResponseEntity<List<AccUnitPrice>>(prices, HttpStatus.OK);
		} 

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PreAuthorize("hasAuthority('AddPrice')")
	@PostMapping("/addprice")
	public ResponseEntity<AccUnitPrice> addNewPrice(@RequestBody AccUnitPrice acup) {
		User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		AccUnitPrice price = new AccUnitPrice();
		if(acup != null) {
			price.setStartDate(acup.getStartDate());
			price.setEndDate(acup.getEndDate());
			price.setPrice(acup.getPrice());
			
			//price.setAccommodationUnit(new AccommodationUnit());
			
			accommodationObjectService.addNewPrice(price);
			logger.info("user: {}, id: {} | D0N0C3 | success", u.getId(), price.getId());
			return new ResponseEntity<AccUnitPrice>(price, HttpStatus.OK);
		}
		logger.error("user: {} | D0N0C3 | failed", u.getId());
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getOne/{id}")
	public AccommodationObjectDTO getObj(@PathVariable Long id){
		AccommodationObject acc = accommodationObjectService.getOneAccObj(id);
		AccommodationObjectDTO accDto = new AccommodationObjectDTO(acc.getId(),acc.getName(), acc.getAddress().getId(), acc.getDescription(), acc.getCategory().getId(), acc.isFreeCancelation(), acc.getDaysToCancel(), acc.getType().getId());
		return accDto;
	}
	
	@PreAuthorize("hasAuthority('AddAgents')")
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
		User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
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
			logger.info("user: {}, id: {} | D0N05J | success", u.getId(), newAcu.getId());
			return new ResponseEntity<AccommodationUnit>(newAcu, HttpStatus.OK);
		}
		logger.error("user: {} | D0N05J | failed", u.getId());
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
	
	@PreAuthorize("hasAuthority('AddContent')")
	@GetMapping("/types/{id}")
	public ResponseEntity<Type> getOneType(@PathVariable Long id) {
		Type ty = new Type();
		ty = typerepo.getOne(id);
		return new ResponseEntity<Type>(ty, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('AddContent')")
	@GetMapping("/categories/{id}")
	public ResponseEntity<Category> getOneCategory(@PathVariable Long id) {
		Category ty = new Category();
		ty = catrepo.getOne(id);
		return new ResponseEntity<Category>(ty, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('AddContent')")
	@GetMapping("/additionalservices/{id}")
	public ResponseEntity<AdditionalService> getOneAdditionalService(@PathVariable Long id) {
		AdditionalService ty = new AdditionalService();
		ty = additonalRepo.getOne(id);
		return new ResponseEntity<AdditionalService>(ty, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('DeleteCodebook')")
	@DeleteMapping("/types/{id}")
	public ResponseEntity<?> deleteOneType(@PathVariable Long id) {	
		User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		typerepo.deleteById(id);
		logger.info("user: {}, id: {} | BRJ3T1 | success", u.getId(), id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('DeleteCodebook')")
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<?> deleteOneCategory(@PathVariable Long id) {
		User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		catrepo.deleteById(id);
		logger.info("user: {}, id: {} | BRJ3C4 | success", u.getId(), id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('DeleteCodebook')")
	@DeleteMapping("/additionalservices/{id}")
	public ResponseEntity<?> deleteOneAdditionalService(@PathVariable Long id) {
		User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		additonalRepo.deleteById(id);
		logger.info("user: {}, id: {} | BRJ3AS | success", u.getId(), id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('AddContent')")
	@PostMapping("/types")
	public ResponseEntity<Type> addType(@RequestBody Type type) {
		//User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		Type ty = new Type();
		ty.setAccObj(new ArrayList<AccommodationObject>());
		ty.setName(type.getName());
		typerepo.save(ty);
		//logger.info("user: {}, id: {} | D0J3T1 | success", u.getId(), ty.getId());
		return new ResponseEntity<Type>(ty, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('AddContent')")
	@PostMapping("/categories")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		//User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		Category cat = new Category();
		cat.setAccObj(new ArrayList<AccommodationObject>());
		cat.setName(category.getName());
		catrepo.save(cat);
		//logger.info("user: {}, id: {} | D0J3C4 | success", u.getId(), category.getId());
		return new ResponseEntity<Category>(cat, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('AddContent')")
	@PostMapping("/additionalservices")
	public ResponseEntity<AdditionalService> addAdditionalService(@RequestBody AdditionalService addser) {
		AdditionalService as = new AdditionalService();
		as.setAccommodationUnits(new ArrayList<AccommodationUnit>());
		as.setIncluded(addser.isIncluded());
		as.setPrice(addser.getPrice());
		as.setName(addser.getName());
		as.setAccommodationObject(accommodationObjectRepository.getOne(Long.valueOf(1)));
		additonalRepo.save(as);
		//User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		//logger.info("user: {}, id: {} | D0J3AS | success", u.getId(), addser.getId());
		return new ResponseEntity<AdditionalService>(as, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('EditCodebook')")
	@PostMapping("/types/{id}")
	public ResponseEntity<Type> updateType(@PathVariable Long id, @RequestBody Type type) {
		Type ty = typerepo.getOne(type.getId());
		ty.setName(type.getName());
		typerepo.save(ty);
		User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		logger.info("user: {}, id: {} | UPJ3T1 | success", u.getId(), type.getId());
		return new ResponseEntity<Type>(ty, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('EditCodebook')")
	@PostMapping("/categories/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
		User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		Category cat = catrepo.getOne(category.getId());
		cat.setName(category.getName());
		catrepo.save(cat);
		logger.info("user: {}, id: {} | UPJ3C4 | success", u.getId(), category.getId());
		return new ResponseEntity<Category>(cat, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('EditCodebook')")
	@PostMapping("/additionalservices/{id}")
	public ResponseEntity<AdditionalService> updateAdditionalService(@PathVariable Long id, @RequestBody AdditionalService addser) {
		User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		AdditionalService as = additonalRepo.getOne(addser.getId());
		as.setName(addser.getName());
		as.setPrice(addser.getPrice());
		additonalRepo.save(as);
		logger.info("user: {}, id: {} | UPJ3AS | success", u.getId(), addser.getId());
		return new ResponseEntity<AdditionalService>(as, HttpStatus.OK);
	}
	
	

}