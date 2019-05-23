package com.ftn.accommodationservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ftn.accommodationservice.filter.AuthenticationTokenFilter;


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
	                .authenticated();
    }
	
}
