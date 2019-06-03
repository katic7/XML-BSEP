package com.ftn.authservice.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class TestRating {

	@Min(1)
	@Max(5)
	private int stars;

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}
	
	
}
