package com.ftn.accommodationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.accommodationservice.model.Image;
import com.ftn.accommodationservice.repository.ImageRepository;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins="http://localhost:4200")
public class ImageController {
	

	@Autowired
	private ImageRepository imRepo;
	
	@PostMapping("/{id}")
	public ResponseEntity<?> uploadAccomodationProfilePicture(@PathVariable Long id, String path) throws Exception {
		
		
	    byte[] arrayPic = new byte[(int) path.getBytes().length];
	    
	    Image blackImage = new Image(1, "pic1", "png", arrayPic);
	    imRepo.save(blackImage);
	    
	    return new ResponseEntity<>("Uploaded!", HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAccommodationProfilePicture(@PathVariable Long id) {
		Image im = imRepo.getOne(id);
		return new ResponseEntity<>(im.getPic(), HttpStatus.OK);
	}

}
