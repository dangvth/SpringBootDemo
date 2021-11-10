package com.tma.SpringBootDemo.controller;

import java.util.Date;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.tma.SpringBootDemo.exception.ErrorMessage;
import com.tma.SpringBootDemo.utils.LogUtil;

/**
 * Handle exception
 * 
 * @author dangv
 *
 */
@RestControllerAdvice
public class BaseController {

	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Handle data not found
	 * 
	 * @param ex the exception to handle
	 * @return the {@link ResponseEntity}
	 */
	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<ErrorMessage> handleDataNotFound(NoSuchElementException ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage("NOT FOUND DATA EXCEPTION-Given param not exist in the database");
		errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
		errorMessage.setTime(new Date());
		LogUtil.logError(logger, ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
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
		errorMessage.setMessage("BAD REQUEST-Given param is not matched to controller");
		errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
		errorMessage.setTime(new Date());
		LogUtil.logError(logger, ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle internal server error
	 * 
	 * @param ex the exception to handle
	 * @return the {@link ResponseEntity}
	 */
	@ExceptionHandler(EmptyResultDataAccessException.class)
	protected ResponseEntity<ErrorMessage> handleInternalServer(EmptyResultDataAccessException ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage("INTERNAL EXCEPTION-exception that occurred somewhere in the code");
		errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setTime(new Date());
		LogUtil.logError(logger, ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handle unknown error
	 * 
	 * @param ex the exception to handle
	 * @return the {@link ResponseEntity}
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorMessage> handleUnknon(Exception ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage("Unknown error");
		errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setTime(new Date());
		LogUtil.logError(logger, ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
