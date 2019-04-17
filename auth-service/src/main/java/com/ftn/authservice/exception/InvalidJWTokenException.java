package com.ftn.authservice.exception;

public class InvalidJWTokenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5799605029633953931L;

	public InvalidJWTokenException(Throwable e) {
		super(e);
	}
	
}
