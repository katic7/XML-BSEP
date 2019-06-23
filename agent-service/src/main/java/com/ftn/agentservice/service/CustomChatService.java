package com.ftn.agentservice.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.ftn.agentservice.chat.ChatGrpc.ChatImplBase;
import com.ftn.agentservice.chat.Message;
import com.ftn.agentservice.model.User;
import com.ftn.agentservice.repository.UserRepository;

import io.grpc.stub.StreamObserver;

public class CustomChatService extends ChatImplBase {
	public static List<User> users = new ArrayList<User>();
	public static List<String> tempUsers = new ArrayList<String>();
	private static LinkedHashSet<StreamObserver<Message>> observers = new LinkedHashSet<>();
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public StreamObserver<Message> join(StreamObserver<Message> responseObserver) {
		observers.add(responseObserver);
		return new StreamObserver<Message>() {

			@Override
			public void onNext(Message value) {
				
				/*User u = userRepo.findByEmail(value.getUser()).get();
				if(!users.contains(u)) {
					users.add(u);
				}
				
				users.forEach(uf -> {
					System.out.println(uf.getName() + " " + uf.getSurname());
				});*/
				
				
				if(!tempUsers.contains(value.getUser())) {
					tempUsers.add(value.getUser());
				}
				
				tempUsers.forEach(us->{
					System.out.println(us);
				});
				
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				
			}
		};
	}

	@Override
	public void send(Message request, StreamObserver<Message> responseObserver) {
		// TODO Auto-generated method stub
		System.out.println(request.getUser() + " " + request.getText());
		observers.add(responseObserver);
	}
	

}
