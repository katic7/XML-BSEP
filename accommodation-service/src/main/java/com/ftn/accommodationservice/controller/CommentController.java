package com.ftn.accommodationservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ftn.accommodationservice.repository.AccommodationUnitRepository;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	private RestTemplate template;
	
	@Autowired
	private AccommodationUnitRepository aunitRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@PostMapping("/visibility")
	@PreAuthorize("hasAuthority('ApproveComment')")
	public ResponseEntity<?> commentVisibility(@RequestBody CommentVisibilityDTO cv){
		/*Comment com = commentRepository.getOne(cv.getId());
		com.setVisible(cv.isFlag());
		commentRepository.save(com);
		return new ResponseEntity<String>("SET VISIBILITY!", HttpStatus.OK);*/
	
		String response = template.postForObject(
				"http://localhost:8139/publishComment",
				cv, String.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
		
	}
	
	@PostMapping("/rating")
	@PreAuthorize("hasAuthority('PublishComment')")
	public ResponseEntity<?> postRating(@RequestBody RatingDTO rating){		
		HttpEntity<RatingDTO> request = new HttpEntity<RatingDTO>(rating);
		String _return= template.postForObject("http://localhost:8135/newRating",
				request, String.class);
		logger.info("reservationID: {}, comment: {} | P0SK0M | success", rating.getReservationID(), rating.getComment() );
		return new ResponseEntity<String>(_return, HttpStatus.OK);
	}
	
	@GetMapping("/ratings")
	@PreAuthorize("hasAuthority('GetComments')")
	public List<RatingDTO> getAllRatings(){
		ResponseEntity<List<RatingDTO>> response = template.exchange(
				"http://localhost:8134/getAllRatings",
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
									"http://localhost:8136/getSpecificRating?id="+id,
									HttpMethod.GET,
									null, 
									new ParameterizedTypeReference<RatingDTO>(){});
		return response.getBody();
	}
	
	@GetMapping("/ratings/accommodation/{id}")
	public List<RatingDTO> getRatingsFromSpecificAcc(@PathVariable Long id){
		ResponseEntity<List<RatingDTO>> response = template.exchange(
				"http://localhost:8137/getRatingsFromSpecificAcc?accommodationID="+id,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<RatingDTO>>(){});
		List<RatingDTO> ratingss = response.getBody();
		return ratingss;
	}
	
	@GetMapping("/ratings/specificAccommodation/{id}")
	public double getRatingScore(@PathVariable Long id){
		ResponseEntity<RatingScoreDTO> response = template.exchange(
				"http://localhost:8138/getRatingScore?id="+id,
				HttpMethod.GET,
				null, 
				new ParameterizedTypeReference<RatingScoreDTO>(){});
		double ratingScore = response.getBody().getRatingScore();
		System.out.println(ratingScore);
		return ratingScore;
	}
	
	@GetMapping("/ratings/published/accommodation/{id}")
	public List<RatingDTO> getPublishedRatingsOfAccommodation(@PathVariable Long id){
		ResponseEntity<List<RatingDTO>> response = template.exchange(
				"http://localhost:8445/getPublishedCommentsOfAccommodation?accommodationID="+id,
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<RatingDTO>>(){});
		List<RatingDTO> ratingss = response.getBody();
		return ratingss;
	}
	
	@GetMapping("/ratings/published")
	public List<RatingDTO> getAllPublishedRatings(){
		ResponseEntity<List<RatingDTO>> response = template.exchange(
				"http://localhost:8134/getPublishedComments",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<RatingDTO>>(){});
		List<RatingDTO> ratingss = response.getBody();
		return ratingss;
	}
	
	
	
	
	
}
