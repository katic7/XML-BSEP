package com.ftn.reservationservice.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ftn.reservationservice.filter.AuthenticationTokenFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	securedEnabled = true,
	jsr250Enabled = true,
	prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
    public AuthenticationTokenFilter authenticationFilter() throws Exception {
		AuthenticationTokenFilter authenticationFilter = new AuthenticationTokenFilter();

        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		
		.authorizeRequests()

		.antMatchers("/api/*").permitAll()
		.antMatchers("/ws/*").permitAll()
		.antMatchers("/ws").permitAll()
		.antMatchers("/api/addresses/test2").permitAll()
		.antMatchers("/api/comment/rating").permitAll()
		.antMatchers("/api/reservations/getfreeunits").permitAll()
		.antMatchers("/api/reservations/*").permitAll()
		.antMatchers("/api/reservations/getfreeunits").permitAll()
		.antMatchers("/api/addresses/*").permitAll()
    	.antMatchers("/api/reservations/getOneUnit/*").permitAll()
    	//.antMatchers("/api/reservations/getForCompletion").permitAll()
		.anyRequest().authenticated().and()
		
		
		.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	http.csrf().disable();
    	
       /* http
	        .cors()
	            .and()
            .headers().frameOptions()
            	.disable()
            	.and()
	        .csrf()
	            .disable()
	            .authorizeRequests()
	            .anyRequest().authenticated()
	            .and()
	            .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .x509().subjectPrincipalRegex("CN=(.*?)(?:,|$)")
	             .and()
	        .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	        .authorizeRequests().anyRequest()
	                .authenticated();*/
    }
	
}
