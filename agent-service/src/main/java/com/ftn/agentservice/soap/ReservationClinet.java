package com.ftn.agentservice.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.ftn.accommodationservice.xsd.GetTestRequest;
import com.ftn.accommodationservice.xsd.GetTestResponse;
import com.ftn.accommodationservice.xsd.PostReservationCompletionRequest;
import com.ftn.accommodationservice.xsd.PostReservationCompletionResponse;
import com.ftn.agentservice.repository.ReservationRepository;

public class ReservationClinet extends WebServiceGatewaySupport {
	
	@Autowired
	private ReservationRepository rrepo;
	
	public GetTestResponse test(int id) {
		GetTestRequest req = new GetTestRequest();
		req.setId(1);
		return (GetTestResponse) getWebServiceTemplate().marshalSendAndReceive(req);
	}
	
	
	public PostReservationCompletionResponse completeReservation(PostReservationCompletionRequest request) {
		return (PostReservationCompletionResponse) getWebServiceTemplate().marshalSendAndReceive(request);
	}
}
