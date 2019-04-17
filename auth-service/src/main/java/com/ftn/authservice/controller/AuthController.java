package com.ftn.authservice.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.authservice.dto.ProfileDto;
import com.ftn.authservice.exception.InvalidJWTokenException;
import com.ftn.authservice.jwt.JwtTokenProvider;
import com.ftn.authservice.model.RoleName;
import com.ftn.authservice.model.User;
import com.ftn.authservice.repository.RoleRepository;
import com.ftn.authservice.repository.UserRepository;
import com.ftn.authservice.request.LoginRequest;
import com.ftn.authservice.request.SignUpRequest;
import com.ftn.authservice.response.JwtAuthenticationResponse;
import com.ftn.authservice.services.CheckTokenAndPermissions;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtTokenProvider jwtProvider;
    
    @Autowired
    CheckTokenAndPermissions permissions;
    
    @RequestMapping("/secured")
	public String secured(){
		return "Pozdrav " + new Date();
	}

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	try {
    		
    		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
	                loginRequest.getEmail(),
	                loginRequest.getPassword()
	         );
	         
    		Authentication authentication = authenticationManager.authenticate(
	            token
	        );
    		String email = authentication.getName();
    		List<String> authorities = authentication.getAuthorities().stream()
    				.map(GrantedAuthority::getAuthority)
    				.collect(Collectors.toList());
    		
    		String jwt = jwtProvider.generateToken(authentication);
    		ProfileDto profile = new ProfileDto(email, authorities, true);
    		
	        return ResponseEntity.ok(new JwtAuthenticationResponse(profile, jwt));
		} catch (AuthenticationException e) {
			return new ResponseEntity<String>("Not logged!", HttpStatus.BAD_REQUEST);
		}
    }
    
    /*@PostMapping("/testSI")
    public ResponseEntity<?> testSI(@Valid @RequestBody LoginRequest loginRequest){
    	
    	User u = userRepository.testLogin(loginRequest.getEmail(),loginRequest.getPassword());
    	return new ResponseEntity<User>(u,HttpStatus.OK);
    }*/
    
    @GetMapping("/check/{token}")
    public ResponseEntity<?> checkToken(@PathVariable String token) throws InvalidJWTokenException{
    	List<String> permisije = permissions.getPermissions(token);
    	String permissije = "";
    	for(String perm : permisije) {
    		permissije += perm+"|";
    	}
    	return new ResponseEntity<String>(permissije, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
       if(userRepository.existsByEmail(signUpRequest.getEmail())) {
           return new ResponseEntity<>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
      }

       if(userRepository.existsByEmail(signUpRequest.getEmail())) {
          return new ResponseEntity<>("Fail -> Email is already in use!",
                   HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
       User user = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),
				Collections.singleton(roleRepository.findByName(RoleName.ROLE_USER)));
        
        
        userRepository.save(user);

        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}