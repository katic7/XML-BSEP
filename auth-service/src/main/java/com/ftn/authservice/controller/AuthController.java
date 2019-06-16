package com.ftn.authservice.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ftn.authservice.dto.ActivateUserDTO;
import com.ftn.authservice.dto.AgentDTO;
import com.ftn.authservice.dto.CreateAgentDTO;
import com.ftn.authservice.dto.ProfileDto;
import com.ftn.authservice.dto.UserDTO;
import com.ftn.authservice.dto.UserStatus;
import com.ftn.authservice.exception.InvalidJWTokenException;
import com.ftn.authservice.jwt.JwtTokenProvider;
import com.ftn.authservice.model.AccommodationObject;
import com.ftn.authservice.model.Agent;
import com.ftn.authservice.model.Role;
import com.ftn.authservice.model.RoleName;
import com.ftn.authservice.model.User;
import com.ftn.authservice.repository.AgentRepository;
import com.ftn.authservice.repository.RoleRepository;
import com.ftn.authservice.repository.UserRepository;
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
    AgentRepository agentRepository;
    
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
   
    
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @RequestMapping("/secured")
	public String secured(){
		return "Pozdrav " + new Date();
	}

    @PreAuthorize("hasAuthority('AddUsers')")
    @PostMapping("/activateUser") //dodati permisije
    public ResponseEntity<?> activateUser(@RequestBody ActivateUserDTO acu){
    	User usr = userRepository.getOne(acu.getId());
    	if(acu.getStatus().equals(UserStatus.ACTIVATE)) {
    		usr.setEnabled(acu.isFlag());
    		userRepository.save(usr);
    	}else if(acu.getStatus().equals(UserStatus.BLOCK)){
    		usr.setNonLocked(!acu.isFlag());
    		userRepository.save(usr);
    	}
    	return new ResponseEntity<UserDTO>(new UserDTO(usr), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('AddUsers')")
    @GetMapping("/getAll") //permisije
    public List<UserDTO> getAllUseres(){
    	List<UserDTO> users =  new ArrayList<UserDTO>();
    	for(User u : userRepository.findAll()) {
    		users.add(new UserDTO(u));
    	}
    	return users;
    }
    
    //@PreAuthorize("hasAuthority('AddUsers')")
    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
    	return new ResponseEntity<UserDTO>(new UserDTO(userRepository.getOne(id)), HttpStatus.OK);	
    }
    
   // @PreAuthorize("hasAuthority('AddAgents')")
    @GetMapping("/getOneAgent/{id}")
    public ResponseEntity<?> getAgent(@PathVariable Long id){
    	return new ResponseEntity<AgentDTO>(new AgentDTO(agentRepository.getOne(id)), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('AddUsers')")
    @GetMapping("/getOnlyUsers")
    public ResponseEntity<?> getOnlyUsers(){
    	List<User> users = userRepository.findAll();
    	List<Agent> agenti = agentRepository.findAll();
    	List<UserDTO> povratna = new ArrayList<UserDTO>();
    	
    	for(Agent a : agenti) {
    		users.remove(userRepository.getOne(a.getId()));
    	}
    	for(User u : users) {
    		povratna.add(new UserDTO(u));
    	}
    	return new ResponseEntity<List<UserDTO>>(povratna, HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('AddAgents')")
    @GetMapping("/getAgents")
    public ResponseEntity<List<AgentDTO>> getAgents(){
    	List<Agent> svi = agentRepository.findAll();
    	List<AgentDTO> povratna = new ArrayList<AgentDTO>();
    	for(Agent a : svi) {
    		povratna.add(new AgentDTO(a));
    	}
    	
    	return new ResponseEntity<List<AgentDTO>>(povratna, HttpStatus.OK);
    }
    
    //@PreAuthorize("hasAuthority('AddAgents')")
    @PostMapping("/createAgent")
    public ResponseEntity<?> createAgent(@RequestBody CreateAgentDTO ca){
    	System.out.println(ca.getAccObj() + " " +ca.getUser());
    	User usr = userRepository.getOne(ca.getUser());
    	Agent ag = new Agent(usr);
    	ag.setPib(ca.getPib());
    	if(ca.getAccObj() != null) {
    		RestTemplate template  = new RestTemplate();
    		ag.setAccObj(template.getForObject("https://localhost:8082/api/accobject/getOne/" + ca.getAccObj(), AccommodationObject.class));
    		agentRepository.saveAgent(ag.getPib(),ag.getId(),ag.getAccObj().getId());
    	}else {
    		agentRepository.saveAgent(ag.getPib(),ag.getId(),null);
    	}
    	
    	Set<Role> roles = new HashSet<>();
    	roles.add(roleRepository.findByName(RoleName.ROLE_AGENT));
    	usr.setRoles(roles);
    	userRepository.save(usr);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
 
    @PreAuthorize("hasAuthority('DeleteUsers')")
    @RequestMapping(value="/deleteUser/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
    	User usr = userRepository.getOne(id);
    	userRepository.delete(usr);
    	return new ResponseEntity<>(HttpStatus.OK);
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
			return new ResponseEntity<String>("Not logged! " + e.getMessage(), HttpStatus.BAD_REQUEST);
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
    
    @GetMapping("/getLogged")
    public ResponseEntity<?> getLogged() {
    	System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
    	if(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).isPresent()) {
    		User logged = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    		return new ResponseEntity<User>(logged, HttpStatus.OK);
    	} else {
    		 return new ResponseEntity<>("Fail ->No logged user",
                     HttpStatus.BAD_REQUEST);
    	}   	
    	
    }
    
    @GetMapping("/getCurrentUser")
    public ResponseEntity<?> getCurrent(){
    	User logged = userRepository.findCurrentUser();
    	return new ResponseEntity<UserDTO>(new UserDTO(logged), HttpStatus.OK);
    }
    
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
    
    /*@GetMapping("/confirm/{vtoken}")
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
		
    }*/
    
    @GetMapping("/signout") 
    public void signout() {
    	SecurityContextHolder.clearContext();
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
				        
				        user.setEnabled(true);
				        
				        
				       
				        //emailService.sendNotification(user, confirmationToken, "Welcome to Megatravel.com! Confirm your registration.");
				        
				        userRepository.save(user);
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