package com.ftn.authservice.controller;

import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/test")
public class TestController {

	/** ADMIN ROLE - TEST 
	 * @return
	 */
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String test() {
		return "Protected by ADMIN";
	}
	
	/** USER ROLE - TEST
	 * @return
	 */
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public String test2() {
		return "Protected by USER";
	}
	
	/** ADD CONTENT AUTHORITY - TEST
	 * @return
	 */
	@GetMapping("/addcontent")
	@PreAuthorize("hasAuthority('AddContent')")
	public String addContent() {
		return "From Auth service - > Content Added!";
	}
	
	@GetMapping("/testing")
	public String create() {
		return "From Auth service";
	}
	
	@GetMapping("/https")
	public String test4() {
		RestTemplate rt = new RestTemplate();
		return rt.exchange("https://localhost:8082/api/addresses/testhttps", HttpMethod.GET, null, String.class).getBody();
		//return rt.getForObject("https://localhost:8082/api/addresses/testhttps", String.class);
	}
	
}
