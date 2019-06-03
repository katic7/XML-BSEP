package com.ftn.authservice.dto;

import com.ftn.authservice.model.AccommodationObject;
import com.ftn.authservice.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAgentDTO {
	private Long user;
	private String pib;
	private Long accObj;
	
}