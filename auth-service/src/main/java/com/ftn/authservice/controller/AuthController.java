package com.ftn.authservice.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.GetMapping;
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
import com.ftn.authservice.jwt.JwtBlackList;
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
    
    public static RoleName roleName;
   
    
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @RequestMapping("/secured")
	public String secured(){
		return "Pozdrav " + new Date();
	}

    @PreAuthorize("hasAuthority('AddUsers')")
    @PostMapping("/activateUser") //dodati permisije
    public ResponseEntity<?> activateUser(@RequestBody ActivateUserDTO acu){
    	User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    	InetAddress localhost = null;
		try {
			localhost = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        System.out.println("System IP Address : " + 
                      (localhost.getHostAddress()).trim());
    	User usr = userRepository.getOne(acu.getId());
    	if(acu.getStatus().equals(UserStatus.ACTIVATE)) {
    		usr.setEnabled(acu.isFlag());
    		logger.info("user: {}, id: {} | AKN0U5 | success", u.getId(), usr.getId());
    		userRepository.save(usr);
    	}else if(acu.getStatus().equals(UserStatus.BLOCK)){
    		usr.setNonLocked(!acu.isFlag());
    		logger.info("user: {}, id: {} | BLN0U5 | success", u.getId(), usr.getId());
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
    
    @PreAuthorize("hasAuthority('PublishComment')")
    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
    	return new ResponseEntity<UserDTO>(new UserDTO(userRepository.getOne(id)), HttpStatus.OK);	
    }
    
    @PreAuthorize("hasAuthority('ModifyContent')")
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
    		Set<Role> role = new HashSet<Role>();
    		role = u.getRoles();
    		Role jednaRola = (Role) role.toArray()[0];
    		if(jednaRola.getName() != roleName.ROLE_ADMIN && jednaRola.getName() != roleName.ROLE_SYSTEM_ADMIN) {
    			povratna.add(new UserDTO(u));
    		}
    	}
    	return new ResponseEntity<List<UserDTO>>(povratna, HttpStatus.OK);
    }
    
    //@PreAuthorize("hasAuthority('AddAccUnit')")
    @GetMapping("/getAgents")
    public ResponseEntity<List<AgentDTO>> getAgents(){
    	List<Agent> svi = agentRepository.findAll();
    	List<AgentDTO> povratna = new ArrayList<AgentDTO>();
    	for(Agent a : svi) {
    		povratna.add(new AgentDTO(a));
    	}
    	
    	return new ResponseEntity<List<AgentDTO>>(povratna, HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('AddAgents')")
    @PostMapping("/createAgent")
    public ResponseEntity<?> createAgent(@RequestBody CreateAgentDTO ca){
    	User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    	User usr = userRepository.getOne(ca.getUser());
    	Agent ag = new Agent(usr);
    	ag.setPib(ca.getPib());
    	if(ca.getAccObj() != null) {
    		RestTemplate template  = new RestTemplate();
    		ag.setAccObj(template.getForObject("https://localhost:8082/api/accobject/getOne/" + ca.getAccObj(), AccommodationObject.class));
    		logger.info("user: {}, id: {} | KRN0AG | success", u.getId(), usr.getId());
    		agentRepository.saveAgent(ag.getPib(),ag.getId(),ag.getAccObj().getId());
    	}else {
    		logger.info("user: {}, id: {} | KRN0AG | success", u.getId(), usr.getId());
    		agentRepository.saveAgent(ag.getPib(),ag.getId(),null);
    	}
    	
    	Set<Role> roles = new HashSet<>();
    	roles.add(roleRepository.findByName(RoleName.ROLE_AGENT));
    	usr.setRoles(roles);
		logger.info("user: {}, id: {} | KRN0AG | success", u.getId(), usr.getId());
    	userRepository.save(usr);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
 
    @PreAuthorize("hasAuthority('DeleteUsers')")
    @RequestMapping(value="/deleteUser/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
    	User u = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    	User usr = userRepository.getOne(id);
		logger.info("user: {}, id: {} | 0BUSER | success", u.getId(), usr.getId());
    	userRepository.delete(usr);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    int attemps = 0;
    String myEmail = "";
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	InetAddress localhost = null;
		try {
			localhost = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ip = (localhost.getHostAddress()).trim();
    	try {
    		/*if(loginRequest.getEmail().equals(myEmail)) {
    			
    		} else {
    			this.myEmail = loginRequest.getEmail();
    			this.attemps = 0;
    		}*/

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

			logger.error("userIP: {} | PRN4SI | failed", ip);

			/*attemps++;
			if(attemps == 3) {
				User r = userRepository.findByEmail(loginRequest.getEmail()).get();
				r.setNonLocked(false);
				userRepository.save(r);
				logger.error("ID: {} | LCKD | error", r.getId() );
			}*/
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
    	   return new ResponseEntity<UserDTO>(new UserDTO(loginUser), HttpStatus.OK);	
    }
    
    @GetMapping("/validEmail/{email}")
    public ResponseEntity<?> validEmail(@PathVariable String email) {
    	InetAddress localhost = null;
		try {
			localhost = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ip = (localhost.getHostAddress()).trim();
        System.out.println("System IP Address : " + 
                      (localhost.getHostAddress()).trim());
    	if(userRepository.findByEmail(email).isPresent()) {
    		logger.error("userIP: {} | R3USER | failed", ip);
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
    		return new ResponseEntity<UserDTO>(new UserDTO(logged), HttpStatus.OK);
    	} else {
    		 return new ResponseEntity<>("Fail ->No logged user",
                     HttpStatus.BAD_REQUEST);
    	}   	
    	
    }
    
    @PreAuthorize("hasAuthority('UpdateUser')")
    @PostMapping("/updateUser")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User u){
    	User usr = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    	User updatedUser = userRepository.getOne(u.getId());
    	updatedUser.setName(u.getName());
    	updatedUser.setSurname(u.getSurname());
    	updatedUser.setEmail(u.getEmail());
    	updatedUser.setTelephone(u.getTelephone());
    	userRepository.save(updatedUser);
		logger.info("user: {}, id: {} | UPUSER | success", usr.getId(), updatedUser.getId());
    	
    	return new ResponseEntity<UserDTO>(new UserDTO(updatedUser), HttpStatus.OK);

    	   	
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
    public ResponseEntity<?> signout(HttpServletRequest request) {
    	System.out.println("LOGOUT");
    	SecurityContextHolder.getContext().setAuthentication(null);
    	SecurityContextHolder.clearContext();
    	JwtBlackList.lista.add(request.getHeader("Authorization").substring(7, request.getHeader("Authorization").length()));
		return null;
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
				        
				        
				       
				        //emailService.sendNotification(user, confirmationToken, "Welcome to Megatravel.com! Confirm your registration.");
				        userRepository.save(user);
						logger.info("user: {} | R3USER | success", user.getId());
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