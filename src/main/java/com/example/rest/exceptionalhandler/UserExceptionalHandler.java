package com.example.rest.exceptionalhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.rest.exceptions.UserNotFoundByIdException;
import com.example.rest.util.AppResponseBuilder;
import com.example.rest.util.ErrorStructure;


@RestControllerAdvice
public class UserExceptionalHandler {

	private AppResponseBuilder responseBuilder;

	public UserExceptionalHandler(AppResponseBuilder responseBuilder) {
		super();
		this.responseBuilder = responseBuilder;
	}

	@ExceptionHandler(UserNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> handleUserNotFoundById(UserNotFoundByIdException ex){
		return responseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage(),"user not found by the given ID");
	}
}



/*
 * public ResponseEntity<ErrorStructure> create(HttpStatus status,String
 * message,String rootCause ){ ErrorStructure error=new ErrorStructure();
 * error.setStatus(status.value()); error.setMessage(message);
 * error.setRootCause(rootCause); return ResponseEntity .status(status)
 * .body(error); }
 */