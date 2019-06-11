package com.ftn.accommodationservice.filter;



import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.client.RestTemplate;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	public AuthenticationTokenFilter() {
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		RestTemplate restTemplate = new RestTemplate();	    
	    //String token = (httpRequest.getHeader("Authorization")).substring(7, httpRequest.getHeader("Authorization").length());
	   // String permisije = restTemplate.getForObject("https://localhost:8085/api/auth/check/{token}", String.class, token);
	    //String username = restTemplate.getForObject("https://localhost:8085/api/auth/check/{token}/username", String.class, token);
		String username = httpRequest.getHeader("username");
		String permisije = httpRequest.getHeader("Permissions");
	    
	    System.out.println(username);
		System.out.println(permisije);
		if(username != null && permisije != null && !username.equals("") && !permisije.equals("")) {
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			
			String[] tokens = permisije.split("\\|");
			for(String tokene : tokens) {
				
				authorities.add(new SimpleGrantedAuthority(tokene));
			}
			
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username,  null, authorities);
			
			
			auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
		chain.doFilter(request, response);
	}
	
}
