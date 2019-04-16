package com.ftn.authservice.jwt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.ftn.authservice.model.Role;
import com.ftn.authservice.model.User;
import com.ftn.authservice.repository.UserRepository;
import com.ftn.authservice.services.UserPrinciple;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${auth.app.jwtSecret}")
    private String jwtSecret;

    @Value("${auth.app.jwtExpiration}")
    private int jwtExpiration;
    
    @Autowired
    private UserRepository repo;

    public String generateJwtToken(Authentication authentication) {

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
		                .setSubject((userPrincipal.getUsername()))
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
		                .signWith(SignatureAlgorithm.HS512, jwtSecret)
		                .compact();
    }
    
    public UserPrinciple getUserPrincipal(String jwt) throws Exception  {
		try {
			Claims claims = getClaimsFromToken(jwt);
			User u = repo.findByUsername(getUsernameFromClaims(claims)).get();
			return new UserPrinciple(
				u.getId().toString(),
				u.getUsername(), 
				getGrantedAuthoritiesFromClaims(u.getRoles())
				
			);
		} catch (Exception e) {
			throw new Exception();
		}		
	}
    
    private Claims getClaimsFromToken(String authToken) throws Exception {
		return Jwts.parser()
			.setSigningKey(jwtSecret)
			.parseClaimsJws(authToken)
			.getBody();
	}
	
	private String getUsernameFromClaims(Claims claims) {
		return claims.getSubject();
	}
	
	private List<GrantedAuthority> getGrantedAuthoritiesFromClaims(Set<Role> roles) {
	
		List<String> authorities = new ArrayList<String>();	
		roles.forEach(r -> {
			authorities.add(r.getName().toString());
		});
		System.out.println(authorities.toString());
		return authorities.stream()
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
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
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(jwtSecret)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }
}