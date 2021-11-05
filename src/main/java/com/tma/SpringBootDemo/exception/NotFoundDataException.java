package com.tma.SpringBootDemo.exception;

public class NotFoundDataException extends Exception {

	/**
	 * Initialize
	 * @param message the message to initialize 
	 */
	public NotFoundDataException(String message) {
		super(message);
	}

}
