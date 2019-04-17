package com.ftn.authservice.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ftn.authservice.exception.InvalidJWTokenException;
import com.ftn.authservice.jwt.JwtTokenProvider;
import com.ftn.authservice.jwt.UserPrincipal;
import com.ftn.authservice.model.Permission;
import com.ftn.authservice.model.Role;
import com.ftn.authservice.model.RoleName;
import com.ftn.authservice.repository.RoleRepository;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class CheckTokenAndPermissions {
	
	@Value("${auth.app.jwtSecret}")
	private String jwtSecret;
	
	private static final Logger logger = LoggerFactory.getLogger(CheckTokenAndPermissions.class);
	
	@Autowired
	private JwtTokenProvider jwtProvider;
	
	@Autowired
	private RoleRepository roleRepo;
	
	public List<String> getPermissions(String authToken) throws InvalidJWTokenException {
		if(validateJwtToken(authToken)) {
			UserPrincipal up = jwtProvider.getUserPrincipal(authToken);
			List<String> permissions = new ArrayList<String>();
			up.getAuthorities().forEach(role -> {
				Role r = roleRepo.findByName(getRoleName(role.getAuthority()));
				for(Permission p : r.getPermissions()) {
					if(!permissions.contains(p.getName())) {
						permissions.add(p.getName());
					}
				}
			});
			return permissions;
		}
		
		return null;
	}
	
	public RoleName getRoleName(String role) {
		if(role.equals(RoleName.ROLE_ADMIN.toString())) {
			return RoleName.ROLE_ADMIN;
		} else if(role.equals(RoleName.ROLE_AGENT.toString())) {
			return RoleName.ROLE_AGENT;
		} else {
			return RoleName.ROLE_USER;
		}
	}
	
	public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        
        return false;
    }
}
