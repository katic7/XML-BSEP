package com.ftn.accommodationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.ftn.accommodationservice.dto.CommentVisibilityDTO;
import com.ftn.accommodationservice.dto.RatingDTO;
import com.ftn.accommodationservice.dto.RatingScoreDTO;
import com.ftn.accommodationservice.model.AccommodationUnit;
import com.ftn.accommodationservice.repository.AccommodationUnitRepository;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	private RestTemplate template;
	
	@Autowired
	private AccommodationUnitRepository aunitRepository;
	
	@PostMapping("/visibility")
	public ResponseEntity<?> commentVisibility(@RequestBody CommentVisibilityDTO cv){
		/*Comment com = commentRepository.getOne(cv.getId());
		com.setVisible(cv.isFlag());
		commentRepository.save(com);
		return new ResponseEntity<String>("SET VISIBILITY!", HttpStatus.OK);*/
		String response = template.postForObject(
				"http://localhost:8010/reservation-cloud-service/us-central1/publishComment",
				cv, String.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
		
	}
	
	@PostMapping("/rating")
	@PreAuthorize("hasAuthority('CREATE')")
	public ResponseEntity<?> postRating(@RequestBody RatingDTO rating){
		HttpEntity<RatingDTO> request = new HttpEntity<RatingDTO>(rating);
		String _return= template.postForObject("http://localhost:8010/reservation-cloud-service/us-central1/newRating",
								request, String.class);
		return new ResponseEntity<String>(_return, HttpStatus.OK);
	}
	
	@GetMapping("/ratings")
	@PreAuthorize("hasAuthority('CREATE')")
	public List<RatingDTO> getAllRatings(){
		ResponseEntity<List<RatingDTO>> response = template.exchange(
				"http://localhost:8010/reservation-cloud-service/us-central1/getAllRatings",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<RatingDTO>>(){});
		List<RatingDTO> ratingss = response.getBody();
		return ratingss;
	}
	
	@GetMapping("/ratings/{id}")
	@PreAuthorize("hasAuthority('CREATE')")
	public RatingDTO getSpecificRating(@PathVariable Long id){
		ResponseEntity<RatingDTO> response = template.exchange(
									"http://localhost:8010/reservation-cloud-service/us-central1/getSpecificRating?id="+id,
									HttpMethod.GET,
									null, 
									new ParameterizedTypeReference<RatingDTO>(){});
		return response.getBody();
	}
	
	@GetMapping("/ratings/accommodation/{id}")
	@PreAuthorize("hasAuthority('CREATE')")
	public List<RatingDTO> getRatingsFromSpecificAcc(@PathVariable Long id){
		ResponseEntity<List<RatingDTO>> response = template.exchange(
				"http://localhost:8010/reservation-cloud-service/us-central1/getRatingsFromSpecificAcc?accommodationID="+id,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<RatingDTO>>(){});
		List<RatingDTO> ratingss = response.getBody();
		return ratingss;
	}
	
	@GetMapping("/ratings/specificAccommodation/{id}")
	@PreAuthorize("hasAuthority('CREATE')")
	public AccommodationUnit getRatingScore(@PathVariable Long id){
		ResponseEntity<RatingScoreDTO> response = template.exchange(
				"http://localhost:8010/reservation-cloud-service/us-central1/getRatingScore?id="+id,
				HttpMethod.GET,
				null, 
				new ParameterizedTypeReference<RatingScoreDTO>(){});
		Long accId = response.getBody().getAccommodationID();
		double ratingScore = response.getBody().getRatingScore();
		AccommodationUnit ac = aunitRepository.getOne(accId);
		ac.setRating(ratingScore);	
		return aunitRepository.save(ac);
	}
	
	@GetMapping("/ratings/published/accommodation/{id}")
	@PreAuthorize("hasAuthority('CREATE')")
	public List<RatingDTO> getPublishedRatingsOfAccommodation(@PathVariable Long id){
		ResponseEntity<List<RatingDTO>> response = template.exchange(
				"http://localhost:8010/reservation-cloud-service/us-central1/getPublishedCommentsOfAccommodation?accommodationID="+id,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<RatingDTO>>(){});
		List<RatingDTO> ratingss = response.getBody();
		return ratingss;
	}
	
	@GetMapping("/ratings/published")
	@PreAuthorize("hasAuthority('CREATE')")
	public List<RatingDTO> getAllPublishedRatings(){
		ResponseEntity<List<RatingDTO>> response = template.exchange(
				"http://localhost:8010/reservation-cloud-service/us-central1/getPublishedComments",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<RatingDTO>>(){});
		List<RatingDTO> ratingss = response.getBody();
		return ratingss;
	}
	
	
	
	
	
}
