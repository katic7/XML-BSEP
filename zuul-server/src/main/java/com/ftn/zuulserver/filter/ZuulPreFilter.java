package com.ftn.zuulserver.filter;


import com.netflix.zuul.ZuulFilter;


public class ZuulPreFilter extends ZuulFilter {

	  //private static Logger log = LoggerFactory.getLogger(ZuulPreFilter.class);


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
	    /*RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    RestTemplate restTemplate = new RestTemplate();
	    log.info("TOKEN------>" + request.getHeader("Authorization"));
	    String token = (request.getHeader("Authorization")).substring(7, request.getHeader("Authorization").length());
	    log.info(token);
	    String roles = restTemplate.getForObject("https://localhost:8443/api/auth/check/{token}", String.class, token);
	    log.info("token je validan i role su -> " + roles);
	    ctx.addZuulRequestHeader("Roles", roles);
	    ctx.addZuulRequestHeader("Authorization", "Bearer "+token);*/
	    return null;
	  }

	}