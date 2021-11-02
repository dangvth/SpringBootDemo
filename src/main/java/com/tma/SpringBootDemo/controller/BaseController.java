package com.tma.SpringBootDemo.controller;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.tma.SpringBootDemo.exception.ErrorMessage;

@RestControllerAdvice
public class BaseController {

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage notFoundDataException(Exception ex, WebRequest request) {
        return new ErrorMessage(new Date(), "Cannot found for this id: " + request.getDescription(false).split("/")[request.getDescription(false).split("/").length-1]);
    }
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage internalException(Exception ex, WebRequest request) {
        return new ErrorMessage(new Date(), ex.getMessage());
    }
}
