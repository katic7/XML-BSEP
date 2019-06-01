package com.ftn.accommodationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
	
	private Long id;
	private Long userID;
	private String comment;
	private Integer rating;
	
}
