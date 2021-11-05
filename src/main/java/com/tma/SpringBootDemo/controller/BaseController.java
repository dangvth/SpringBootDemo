package com.tma.SpringBootDemo.controller;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.tma.SpringBootDemo.exception.ErrorMessage;

/**
 * Handle exception
 */
@RestControllerAdvice
public class BaseController {

	/**
	 * Handle data not found
	 * 
	 * @param ex the exception to handle
	 * @return the {@link ResponseEntity}
	 */
	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<ErrorMessage> handleDataNotFound(NoSuchElementException ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage("Given param not exist in the database");
		errorMessage.setStatus(HttpStatus.NOT_FOUND);
		errorMessage.setTime(new Date());
		return new ResponseEntity<ErrorMessage>(errorMessage, errorMessage.getStatus());
	}

	/**
	 * Handle bad request
	 * 
	 * @param ex the exception to handle
	 * @return the {@link ResponseEntity}
	 */
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class, NumberFormatException.class })
	protected ResponseEntity<ErrorMessage> handleBadRequest(Exception ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage("Given param is not matched to controller");
		errorMessage.setStatus(HttpStatus.BAD_REQUEST);
		errorMessage.setTime(new Date());
		return new ResponseEntity<ErrorMessage>(errorMessage, errorMessage.getStatus());
	}

	/**
	 * Handle internal server error
	 * 
	 * @param ex the exception to handle
	 * @return the {@link ResponseEntity}
	 */
	@ExceptionHandler(EmptyResultDataAccessException.class)
	protected ResponseEntity<ErrorMessage> handleDataNotFound(EmptyResultDataAccessException ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorMessage.setTime(new Date());
		return new ResponseEntity<ErrorMessage>(errorMessage, errorMessage.getStatus());
	}

	/**
	 * Handle unknown error
	 * 
	 * @param ex the exception to handle
	 * @return the {@link ResponseEntity}
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorMessage> handleDataNotFound(Exception ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage("Unknown error");
		errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorMessage.setTime(new Date());
		return new ResponseEntity<ErrorMessage>(errorMessage, errorMessage.getStatus());
	}
}
