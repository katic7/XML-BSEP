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
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		
		.authorizeRequests()

		.antMatchers("/api/*").permitAll()
		.antMatchers("/ws/*").permitAll()
		.antMatchers("/ws").permitAll()
		.antMatchers("/hello").permitAll()
		.antMatchers("/api/accobject/*").permitAll()
		.antMatchers("/api/addresses/test2").permitAll()
		.antMatchers("/api/comment/ratings/specificAccommodation/*").permitAll()
		.antMatchers("/api/addresses").permitAll()
		.antMatchers("/api/addresses/*").permitAll()
		.antMatchers("/api/accobject/getOne/*").permitAll()
		.antMatchers("/api/accobject/getOneUnit/*").permitAll()
		.antMatchers("/api/accobject/getUnits").permitAll()
		.antMatchers("/api/accobject/types").permitAll()
		.antMatchers("/api/accobject/categories").permitAll()
		.antMatchers("/api/accobject/additionalservices").permitAll()
		.antMatchers("/api/comment/ratings/published/accommodation/*").permitAll()
		.antMatchers("/api/comment/ratings/accommodation/*").permitAll()
		
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
