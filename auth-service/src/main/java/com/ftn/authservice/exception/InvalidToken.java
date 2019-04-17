package com.ftn.authservice.exception;

public class InvalidToken extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9097272985041688630L;

	public InvalidToken(String message) {
		super(message);
	}
	
}
