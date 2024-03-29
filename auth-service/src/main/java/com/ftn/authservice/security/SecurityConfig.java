package com.ftn.authservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ftn.authservice.jwt.JwtAuthenticationEntryPoint;
import com.ftn.authservice.jwt.JwtAuthenticationFilter;
import com.ftn.authservice.jwt.SecurityEvaluationContextExtension;
import com.ftn.authservice.services.DomainUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	securedEnabled = true,
	jsr250Enabled = true,
	prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private DomainUserDetailsService domainUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    
    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension(){
        return new SecurityEvaluationContextExtension();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
            .userDetailsService(domainUserDetailsService)
            .passwordEncoder(passwordEncoder());
    }
    
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
		.antMatchers("/api/test/*").permitAll()
		.antMatchers("/api/test/testing").permitAll()
		.antMatchers("/api/addresses/test2").permitAll()
		.antMatchers("/api/comment/rating").permitAll()
		.antMatchers("/api/auth/**").permitAll()
		.antMatchers("/api/auth/getAgents").permitAll()
		.antMatchers("/api/auth/createAgent").permitAll()
		.anyRequest().authenticated().and()
		
		
		.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
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
