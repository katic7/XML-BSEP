package com.ftn.agentservice.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.ftn.accommodationservice.xsd.GetTestRequest;
import com.ftn.accommodationservice.xsd.GetTestResponse;
import com.ftn.agentservice.repository.ReservationRepository;

public class ReservationClinet extends WebServiceGatewaySupport {
	
	@Autowired
	private ReservationRepository rrepo;
	
	public GetTestResponse test(int id) {
		GetTestRequest req = new GetTestRequest();
		req.setId(1);
		return (GetTestResponse) getWebServiceTemplate().marshalSendAndReceive(req);
	}

}
