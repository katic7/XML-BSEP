package com.ftn.agentservice.config;

/*import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {
	
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
	        .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	        .authorizeRequests()
	            .antMatchers("/",
			                 "/favicon.ico",
			                )
	                .permitAll()
                .antMatchers("/ws")
	            	.permitAll();
    }
	

}
*/