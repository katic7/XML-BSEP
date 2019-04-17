package com.ftn.reservationservice.filter;

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

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String username = httpRequest.getHeader("username");
		String permissions = httpRequest.getHeader("Permissions");
		
		if(username != null && permissions != null && !username.equals("") && !permissions.equals("")) {
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			
			String[] tokens = permissions.split("\\|");
			for(String token : tokens) {
				authorities.add(new SimpleGrantedAuthority(token));
			}
			
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username,  null, authorities);
			
			
			auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
		chain.doFilter(request, response);
	}
	
}
