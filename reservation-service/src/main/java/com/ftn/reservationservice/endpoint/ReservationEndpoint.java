package com.ftn.reservationservice.endpoint;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ftn.accommodationservice.xsd.GetTestRequest;
import com.ftn.accommodationservice.xsd.GetTestResponse;
import com.ftn.accommodationservice.xsd.PostReservationCompletionRequest;
import com.ftn.accommodationservice.xsd.PostReservationCompletionResponse;
import com.ftn.accommodationservice.xsd.Test;
import com.ftn.reservationservice.model.Reservation;
import com.ftn.reservationservice.repository.ReservationRepository;

@Endpoint
public class ReservationEndpoint {
	
	@Autowired
	public ReservationRepository resRepo;

	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "GetTestRequest")
	@ResponsePayload
	public GetTestResponse test(@RequestPayload GetTestRequest request) {
		System.out.println("Usao u test");
		GetTestResponse e = new GetTestResponse();
		Test t = new Test();
		t.setName("Nemanja");
		e.setTest(t);
		return e;
	}
	
	@PayloadRoot(namespace = "http://ftn.com/accommodationservice/xsd", localPart = "PostReservationCompletionRequest")
	@ResponsePayload
	@Transactional
	public PostReservationCompletionResponse completeReservation(@RequestPayload PostReservationCompletionRequest request) {
		
		PostReservationCompletionResponse e = new PostReservationCompletionResponse();
		Reservation r = resRepo.getOne(request.getReservationID());
		r.setCompleted(request.isCompleted());
		resRepo.save(r);
		e.setMessage("Completed set to " + request.isCompleted() + ".");
		return e;
	}
	
}
