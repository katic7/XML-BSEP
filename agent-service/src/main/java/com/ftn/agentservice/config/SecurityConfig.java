package com.ftn.agentservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ftn.agentservice.filter.AuthenticationTokenFilter;


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
		.antMatchers("/h2console/*").permitAll()
		.antMatchers("/ws/*").permitAll()
		.antMatchers("/ws").permitAll()
		.antMatchers("/api/accommodations/allAdditionalServices").permitAll()
		.antMatchers("/hello").permitAll()
		.antMatchers("/h2console").permitAll()
		.antMatchers("/api/accommodations/syncDataBase").permitAll()
		.antMatchers("/api/accommodations/testing123").permitAll()
		.antMatchers("/api/accommodations/getImage/*").permitAll()
		.antMatchers("/api/accobject/*").permitAll()
		.antMatchers("/api/accommodations/*").permitAll()
		.antMatchers("/api/addresses/test2").permitAll()
		.antMatchers("/api/comment/*").permitAll()
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
