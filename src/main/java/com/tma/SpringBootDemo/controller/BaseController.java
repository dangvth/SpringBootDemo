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

@RestControllerAdvice
public class BaseController {

	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<ErrorMessage> handleDataNotFound(NoSuchElementException ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage("Given param not exist in the database");
		errorMessage.setStatus(HttpStatus.NOT_FOUND);
		errorMessage.setTime(new Date());
		return new ResponseEntity<ErrorMessage>(errorMessage, errorMessage.getStatus());
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class, NumberFormatException.class })
	protected ResponseEntity<ErrorMessage> handleBadRequest(Exception ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage("Given param is not matched to controller");
		errorMessage.setStatus(HttpStatus.BAD_REQUEST);
		errorMessage.setTime(new Date());
		return new ResponseEntity<ErrorMessage>(errorMessage, errorMessage.getStatus());
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	protected ResponseEntity<ErrorMessage> handleDataNotFound(EmptyResultDataAccessException ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorMessage.setTime(new Date());
		return new ResponseEntity<ErrorMessage>(errorMessage, errorMessage.getStatus());
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorMessage> handleDataNotFound(Exception ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage("Unknow error");
		errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorMessage.setTime(new Date());
		return new ResponseEntity<ErrorMessage>(errorMessage, errorMessage.getStatus());
	}
}
