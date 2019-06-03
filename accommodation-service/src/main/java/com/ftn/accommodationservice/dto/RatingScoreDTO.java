package com.ftn.accommodationservice.dto;

import lombok.Data;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingScoreDTO {
	
	double ratingScore;
	Long accommodationID;

}
