package com.ftn.agentservice.soap;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "AccomodationService", targetNamespace = "http://www.ftn.com/accommodationService", wsdlLocation = "http://localhost:8082/AccommodationService?wsdl")
public class AccommodationWSSetup extends Service{
	
	    private final static URL ACCOMMODATIONWS_WSDL_LOCATION;
	    private final static WebServiceException ACCOMODATIONSERVICE_EXCEPTION;
	    private final static QName ACCOMODATIONSERVICE_QNAME = new QName("http://www.ftn.com/accommodationService", "AccommodationService");

	    static {
	        URL url = null;
	        WebServiceException e = null;
	        try {
	            url = new URL("http://localhost:8082/AccommodationService?wsdl");
	        } catch (MalformedURLException ex) {
	            e = new WebServiceException(ex);
	        }
	        ACCOMMODATIONWS_WSDL_LOCATION = url;
	        ACCOMODATIONSERVICE_EXCEPTION = e;
	    }

	    public AccommodationWSSetup() {
	        super(__getWsdlLocation(), ACCOMODATIONSERVICE_QNAME);
	    }

	    public AccommodationWSSetup(WebServiceFeature... features) {
	        super(__getWsdlLocation(), ACCOMODATIONSERVICE_QNAME, features);
	    }

	    public AccommodationWSSetup(URL wsdlLocation) {
	        super(wsdlLocation, ACCOMODATIONSERVICE_QNAME);
	    }

	    public AccommodationWSSetup(URL wsdlLocation, WebServiceFeature... features) {
	        super(wsdlLocation, ACCOMODATIONSERVICE_QNAME, features);
	    }

	    public AccommodationWSSetup(URL wsdlLocation, QName serviceName) {
	        super(wsdlLocation, serviceName);
	    }

	    public AccommodationWSSetup(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
	        super(wsdlLocation, serviceName, features);
	    }

	    /**
	     * 
	     * @return
	     *     returns AccommodationWebServiceSoap
	     */
	    @WebEndpoint(name = "AccommodationServicePort")
	    public AccommodationWS getAccomodationWebServicePort() {
	        return super.getPort(new QName("http://www.ftn.com/accommodationService", "AccomodationServicePort"), AccommodationWS.class);
	    }

	    /**
	     * 
	     * @param features
	     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
	     * @return
	     *     returns AccomodationWebServiceSoap
	     */
	    @WebEndpoint(name = "AccomodationWebServicePort")
	    public AccommodationWS getAccomodationWebServicePort(WebServiceFeature... features) {
	        return super.getPort(new QName("http://booking.xws.ftn.rs/accomodationWebService", "AccomodationWebServicePort"), AccommodationWS.class, features);
	    }

	    private static URL __getWsdlLocation() {
	        if (ACCOMODATIONSERVICE_EXCEPTION!= null) {
	            throw ACCOMODATIONSERVICE_EXCEPTION;
	        }
	        return ACCOMMODATIONWS_WSDL_LOCATION;
	    }

}
