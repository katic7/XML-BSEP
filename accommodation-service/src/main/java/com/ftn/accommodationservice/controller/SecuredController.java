package com.ftn.accommodationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {

	@GetMapping("/hello")
	public String secureHello() {
		return "I'm secured!";
	}
	
}