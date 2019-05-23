package com.ftn.agentservice.soap;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


@WebService(name = "AccomodationWS", targetNamespace = "http://www.ftn.com/accommodationService")
public interface AccommodationWS {

    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "testRequest", targetNamespace = "http://www.ftn.com/accommodationService", className = "com.ftn.agentservice.soap.TestRequest")
    @ResponseWrapper(localName = "testResponse", targetNamespace = "http://www.ftn.com/accommodationService", className = "com.ftn.agentservice.soap.TestResponse")
    public String testMethod();
	

}
