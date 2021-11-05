package com.tma.SpringBootDemo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorMessage {

	private Date time;
	private HttpStatus status;
	private String message;

	/**
	 * Get time
	 * 
	 * @return the {@link Date}
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * Set time
	 * 
	 * @param time the {@link Date} to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * Get status
	 * 
	 * @return the {@link HttpStatus}
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * Set status
	 * 
	 * @param status the {@link HttpStatus} to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * Get message
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set message
	 * 
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
