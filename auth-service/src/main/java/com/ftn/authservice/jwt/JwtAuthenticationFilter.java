package com.ftn.authservice.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ftn.authservice.exception.InvalidJWTokenException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider tokenProvider;

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain) throws ServletException, IOException {
		String jwt = getJwtFromRequest(request);
		if(JwtBlackList.lista.contains(jwt)) {
			SecurityContextHolder.clearContext();
			return;
		}
		
		if (StringUtils.hasText(jwt)) {
			try {
				UserDetails details = tokenProvider.getUserPrincipal(jwt);
				
				SecurityContextHolder.getContext().setAuthentication(
					new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities())
				);
			} catch (InvalidJWTokenException e) {
				logger.error("Exception thrown {}", e.getMessage());
			}
		}

		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
