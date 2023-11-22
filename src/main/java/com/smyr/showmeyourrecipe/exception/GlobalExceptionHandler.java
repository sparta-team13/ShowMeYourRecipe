package com.smyr.showmeyourrecipe.exception;

import org.springframework.dao.DuplicateKeyException;
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
		HttpStatus hs = HttpStatus.NOT_FOUND;
		final ErrorResponse errorResponse = ErrorResponse.create( ex, hs, ex.getMessage() );

		return new ResponseEntity<>( errorResponse, hs );
	}

	@ExceptionHandler( IllegalArgumentException.class )
	protected ResponseEntity< ErrorResponse > handlerIllegalArgumentException( IllegalArgumentException ex ) {
		HttpStatus hs = HttpStatus.BAD_REQUEST;
		final ErrorResponse errorResponse = ErrorResponse.create( ex, hs, ex.getMessage() );

		return new ResponseEntity<>( errorResponse, hs );
	}

	@ExceptionHandler( DuplicateKeyException.class )
	protected ResponseEntity< ErrorResponse > handlerDuplicateKeyException( DuplicateKeyException ex ) {
		HttpStatus hs = HttpStatus.CONFLICT;
		final ErrorResponse errorResponse = ErrorResponse.create( ex, hs, ex.getMessage() );

		return new ResponseEntity<>( errorResponse, hs );
	}

	@ExceptionHandler( Exception.class )
	protected ResponseEntity< ErrorResponse > handlerException( Exception ex ) {
		HttpStatus hs = HttpStatus.INTERNAL_SERVER_ERROR;
		final ErrorResponse errorResponse = ErrorResponse.create( ex, hs, ex.getMessage() );

		return new ResponseEntity<>( errorResponse, hs );
	}
}
