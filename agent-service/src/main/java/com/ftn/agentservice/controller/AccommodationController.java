package com.ftn.agentservice.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.ftn.accommodationservice.xsd.AccUnitPrice;
import com.ftn.accommodationservice.xsd.AccommodationObject;
import com.ftn.accommodationservice.xsd.AccommodationUnit;
import com.ftn.accommodationservice.xsd.AdditionalService;
import com.ftn.accommodationservice.xsd.Address;
import com.ftn.accommodationservice.xsd.GetAccommodationUnitResponse;
import com.ftn.accommodationservice.xsd.GetAddressResponse;
import com.ftn.accommodationservice.xsd.GetAllAccUnitPriceRequest;
import com.ftn.accommodationservice.xsd.GetAllAccUnitPriceResponse;
import com.ftn.accommodationservice.xsd.GetAllAdditionalServiceResponse;
import com.ftn.accommodationservice.xsd.PostAccUnitPriceRequest;
import com.ftn.accommodationservice.xsd.PostAccUnitPriceResponse;
import com.ftn.accommodationservice.xsd.PostAccommodationObjectResponse;
import com.ftn.accommodationservice.xsd.PostAddressRequest;
import com.ftn.accommodationservice.xsd.PostAddressResponse;
import com.ftn.accommodationservice.xsd.PostObjectUnitsResponse;
import com.ftn.agentservice.dto.AccommodationObjectDTO;
import com.ftn.agentservice.model.Image;
import com.ftn.agentservice.model.User;
import com.ftn.agentservice.repository.AccommodationObjectRepository;
import com.ftn.agentservice.repository.AccommodationUnitRepository;
import com.ftn.agentservice.repository.ImageRepository;
import com.ftn.agentservice.repository.UserRepository;
import com.ftn.agentservice.soap.AccommodationClient;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
	
	@Autowired
	private AccommodationClient client;
	
	@Autowired
	private AccommodationUnitRepository acurepo;
	
	@Autowired
	private ImageRepository imageRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(AccommodationController.class);
	
	@PreAuthorize("hasAuthority('AddAccUnit')")
	@PostMapping
	public String newAcc(@RequestBody AccommodationUnit request) {
		//t
		User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		GetAccommodationUnitResponse r = client.saveNewAcc(request);
		logger.info("user: {}, id: {} | D0N0AU | success", u.getId(), r.getAccommodationUnit().getId());
		return r.getAccommodationUnit().getDescription();	
	}
	
	@GetMapping
	public String test() {
		return "nemanjica";
	}
	
	@PreAuthorize("hasAuthority('AddAccUnit')")
	@GetMapping("/getObjectUnits/{id}")
	public List<AccommodationUnit> getUnits(@PathVariable Long id){
		PostObjectUnitsResponse response = client.getUnits(id);
		return response.getAccommodationUnit();
	}
	
	@GetMapping("/allAdditionalServices")
	public List<AdditionalService> getAllAdditionalServices(){
		GetAllAdditionalServiceResponse as = client.getAllAdditionalServiceResponse();
		return as.getAdditionalServices();
	}
	
	@PreAuthorize("hasAuthority('AddAccUnit')")
	@GetMapping("/getAddress/{id}")
	public Address getAddress(@PathVariable Long id) {
		GetAddressResponse response = client.getAddress(id);
		return response.getAddress();
	}
	
	/*@GetMapping("/getAllPrices")
	public List<AccUnitPrice> gelAllPrices(){
		GetAllAccUnitPriceResponse response = client.getAllPrices();
		return response.getAccUnitPrice();
	}*/
	
	@PreAuthorize("hasAuthority('AddAccUnit')")
	@PostMapping("/createPrice")
	public AccUnitPrice createPrice(@RequestBody AccUnitPrice acc) {
		PostAccUnitPriceResponse response = client.createPrice(acc);
		return response.getAccUnitPrice();
	}
	
	@PreAuthorize("hasAuthority('AddAccUnit')")
	@PostMapping("/createAddress")
	public Address createAddress(@RequestBody Address adr) {
		PostAddressResponse address = client.createAddress(adr);
		adr.setId(address.getAddress().getId());
		return adr;
	}
	
	@PreAuthorize("hasAuthority('AddAccUnit')")
	@PostMapping("/createAccObject")
	public AccommodationObjectDTO createObject(@RequestBody AccommodationObjectDTO accObj, HttpServletRequest request) {
		User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		String token = (request.getHeader("Authorization")).substring(7, request.getHeader("Authorization").length());
		System.out.println(token + "TOKEEEEEN");
		RestTemplate template = new RestTemplate();
		String username = template.getForObject("https://localhost:8085/api/auth/check/{token}/username", String.class, token);
		
		User usr = userRepository.findByEmail(username).get();
		PostAccommodationObjectResponse acc = client.createAccObject(accObj, usr);
		accObj.setId(acc.getAccommodationObject().getId());
		logger.info("user: {}, id: {} | D0N0A0 | success", u.getId(), accObj.getId());
		return accObj;
	}
	
	@PreAuthorize("hasAuthority('AddAccUnit')")
	@PostMapping("/addAccUnit")
	public AccommodationUnit addNewAccUnit(@RequestBody AccommodationUnit accUnit) {
		User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		GetAccommodationUnitResponse r = client.saveNewAcc(accUnit);
		logger.info("user: {}, id: {} | D0N0AU | success", u.getId(), r.getAccommodationUnit().getId());
		return r.getAccommodationUnit();
		
	}
	
	@PreAuthorize("hasAuthority('AddAccUnit')")
	@RequestMapping(value = "/uploadImage/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> uploadFile(@PathVariable @Min(1) Long id,
			@RequestParam("Image") MultipartFile[] request) {
		System.out.print("pogodio image");
		User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		String returnValue = "";
		com.ftn.agentservice.model.AccommodationUnit acc = new com.ftn.agentservice.model.AccommodationUnit();
		acc = acurepo.getOne(id);
		acc.setImage(new ArrayList<Image>());
		List<Image> slike = new ArrayList<>();
		
		for (int i = 0; i < request.length; i++) {
			try {
				// saveImage(request[i]);
				slike.add(saveImages(request[i], acc));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.info("user: {}, id: {} | D0N0SL | success", u.getId(), acc.getId());
		acc.setImage(slike);
		return new ResponseEntity<>( HttpStatus.OK);

	}
	
	public Image saveImages(MultipartFile images, com.ftn.agentservice.model.AccommodationUnit acc) throws IOException {

		String folder = "photos/";
		// byte[] bytes = image.getBytes();
		// Path path = Paths.get(folder + image.getOriginalFilename());
		// System.out.println(path.toAbsolutePath());
		// for (int i = 0; i < images.length; i++) {
		
 		
		
		Path path = Paths.get(folder + images.getOriginalFilename());
		System.out.println(path.toAbsolutePath());
		ClassPathResource backImgFile = new ClassPathResource(path.toAbsolutePath().toString());
		byte[] arrayPic = images.getBytes();
		// backImgFile.getInputStream().read(arrayPic);
		Image blackImage = new Image(images.getOriginalFilename(), arrayPic);
		blackImage.setAccUnit(acc);
		imageRepo.save(blackImage);
		
		//NewAccommodationUnitResponse response = agentSoapController.newAccommodationUnit(savedUnit);
		//System.out.println(response.getAccommodationUnit().getBedNumber() + "IVANA ACC UNIT");
		
		return blackImage;
		// }
		/*
		 * retrieve image from MySQL via SpringJPA for (ImageModel imageModel :
		 * imageRepository.findAll()) { Files.write(Paths.get("retrieve-dir/" +
		 * imageModel.getName() + "." + imageModel.getType()), imageModel.getPic()); }
		 */

	}
}
