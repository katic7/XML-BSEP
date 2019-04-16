package com.ftn.authservice.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.authservice.dto.UserDTO;
import com.ftn.authservice.jwt.JwtProvider;
import com.ftn.authservice.model.Role;
import com.ftn.authservice.model.RoleName;
import com.ftn.authservice.model.User;
import com.ftn.authservice.repository.RoleRepository;
import com.ftn.authservice.repository.UserRepository;
import com.ftn.authservice.request.LoginForm;
import com.ftn.authservice.request.SignUpForm;
import com.ftn.authservice.response.JwtResponse;

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
    JwtProvider jwtProvider;
    
    @RequestMapping("/secured")
	public String secured(){
		return "Pozdrav " + new Date();
	}

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();


        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(), userDetails.getAuthorities()));
    }
    
    @PostMapping("/testSI")
    public ResponseEntity<?> testSI(@Valid @RequestBody LoginForm loginRequest){
    	
    	User u = userRepository.testLogin(loginRequest.getUsername(),loginRequest.getPassword());
    	return new ResponseEntity<UserDTO>(new UserDTO(u),HttpStatus.OK);
    }
    
    @GetMapping("/check/{token}")
    public ResponseEntity<?> checkToken(@PathVariable String token){
    	
    	User u = (userRepository.findByUsername(jwtProvider.getUserNameFromJwtToken(token))).get();
    	String roles = "";
    	for(Role r : u.getRoles()) {
    		roles += r.getName() + ",";
    	}
    	roles=roles.substring(0, roles.length() - 1);
    	
    	return new ResponseEntity<String>(roles, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
       if(userRepository.existsByUsername(signUpRequest.getUsername())) {
           return new ResponseEntity<>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
      }

       if(userRepository.existsByEmail(signUpRequest.getEmail())) {
          return new ResponseEntity<>("Fail -> Email is already in use!",
                   HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
        
        List<Role> roles = new ArrayList<>();
        
        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
        		.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
        		roles.add(userRole);   

        user.getRoles().add(userRole);
        userRepository.save(user);

        return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.CREATED);
    }
}