package com.ftn.zuulserver.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;


public class ZuulPreFilter extends ZuulFilter {

	  private static Logger log = LoggerFactory.getLogger(ZuulPreFilter.class);


	  @Override
	  public String filterType() {
	    return "pre";
	  }

	  @Override
	  public int filterOrder() {
	    return 1;
	  }

	  @Override
	  public boolean shouldFilter() {
	    return true;
	  }

	  @Override
	  public Object run() {
	    RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    RestTemplate restTemplate = new RestTemplate();
	    
	    String token = (request.getHeader("Authorization")).substring(7, request.getHeader("Authorization").length());
	    
	    String permisije = restTemplate.getForObject("http://localhost:8085/api/auth/check/{token}", String.class, token);
	    String username = restTemplate.getForObject("http://localhost:8085/api/auth/check/{token}/username", String.class, token);
	    ctx.addZuulRequestHeader("Permissions", permisije);
	    ctx.addZuulRequestHeader("username", username);
	   
	    log.info("PERMISIJE -> " + permisije);
	    return null;
	  }

	}