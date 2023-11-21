package com.smyr.showmeyourrecipe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler( NoSuchElementException.class )
	protected ResponseEntity< ErrorResponse > handlerNoSuchElementFoundException( NoSuchElementException ex ) {
		final ErrorResponse errorResponse = ErrorResponse.create( ex, HttpStatus.NOT_FOUND, ex.getMessage() );

		return new ResponseEntity<>( errorResponse, HttpStatus.NOT_FOUND );
	}
}
