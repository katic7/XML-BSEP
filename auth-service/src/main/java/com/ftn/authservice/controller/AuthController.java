package com.ftn.authservice.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ftn.authservice.model.VerificationToken;
import com.ftn.authservice.repository.RoleRepository;
import com.ftn.authservice.repository.UserRepository;
import com.ftn.authservice.repository.VerificationTokenRepository;
import com.ftn.authservice.request.LoginRequest;
import com.ftn.authservice.request.SignUpRequest;
import com.ftn.authservice.response.JwtAuthenticationResponse;
import com.ftn.authservice.services.CheckTokenAndPermissions;
import com.ftn.authservice.services.EmailService;

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
    
    @Autowired
    EmailService emailService;
    
    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
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
    		User us = userRepository.findByEmail(loginRequest.getEmail()).get();
    		logger.info("ID: {} | PRN4SI | success", us.getId() );
	        return ResponseEntity.ok(new JwtAuthenticationResponse(profile, jwt));
		} catch (AuthenticationException e) {
			logger.error("PRN4SI | fail");
			return new ResponseEntity<String>("Not logged!", HttpStatus.BAD_REQUEST);
		}
    }
    
    @GetMapping("/checkEmail/{email}")
    public ResponseEntity<?> checkEmail(@PathVariable String email) {
    	User loginUser = userRepository.findByEmail(email).get();
    	if(loginUser == null) {
            return new ResponseEntity<>("Fail -> No email found. Register first",
                     HttpStatus.BAD_REQUEST);
       } 
    	   return new ResponseEntity<User>(loginUser, HttpStatus.OK);	
    }
    
    @GetMapping("/validEmail/{email}")
    public ResponseEntity<?> validEmail(@PathVariable String email) {
    	if(userRepository.findByEmail(email).isPresent()) {
    		return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);	
    	} else {
    		return new ResponseEntity<>(true,
                    HttpStatus.OK);
    	}  	   
    }
    
    @GetMapping("/getLogged/{jwt}")
    public ResponseEntity<?> getLogged(@PathVariable String jwt) throws InvalidJWTokenException {
    	
    	User logged = userRepository.findByEmail(jwtProvider.getUserPrincipal(jwt).getUsername()).get();
    	if(logged == null) {
            return new ResponseEntity<>("Fail ->No logged user",
                     HttpStatus.BAD_REQUEST);
       } else {
    	   return new ResponseEntity<User>(logged, HttpStatus.OK);
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
    
    @GetMapping("/check/{token}/username")
    public ResponseEntity<?> getUsername(@PathVariable String token) throws InvalidJWTokenException{
    	if(permissions.validateJwtToken(token)) {
    		return new ResponseEntity<String>(jwtProvider.getUserPrincipal(token).getUsername(), HttpStatus.OK);
    	}
    	return null;
    }
    
    @GetMapping("/confirm/{vtoken}")
    public ResponseEntity<?> confirmUser(@PathVariable String vtoken) throws InvalidJWTokenException{
    	VerificationToken token = verificationTokenRepository.findByConfirmationToken(vtoken);
    	if(token != null)
        {
            User user = userRepository.findByEmail(token.getUser().getEmail()).get();
            user.setEnabled(true);
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
		
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws MessagingException {
       
       if(userRepository.existsByEmail(signUpRequest.getEmail())) {
          return new ResponseEntity<>("Fail -> Email is already in use!",
                   HttpStatus.BAD_REQUEST);
        }

       if(signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword()))
				try {
					
					
				       User user = new User(signUpRequest.getEmail(), 
				    		   				encoder.encode(signUpRequest.getPassword()),
				    		   				Collections.singleton(roleRepository.findByName(RoleName.ROLE_USER)));
				        
				        user.setEnabled(false);
				        
				        
				        VerificationToken confirmationToken = new VerificationToken(user);
				        //emailService.sendNotification(user, confirmationToken, "Welcome to Megatravel.com! Confirm your registration.");
				        
				        userRepository.save(user);
			            verificationTokenRepository.save(confirmationToken);
			            
				        
	
				        return new ResponseEntity<User>(user, HttpStatus.CREATED);
						   
					   
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		else {
	    	   return new ResponseEntity<>("Fail -> Passwords don't match!",
	                   HttpStatus.BAD_REQUEST);
	       }
	return null;
    }
}