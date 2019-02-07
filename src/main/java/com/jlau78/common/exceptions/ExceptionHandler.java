package com.jlau78.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jlau78.foursquare.response.venue.DetailResponse;
import com.jlau78.foursquare.response.venue.SearchResponse;


/**
 * Handles error messages for return
 */
public class ExceptionHandler {

	public static final String UNEXPECTED_ERROR_MSG = "UNEXPECTED_ERROR_MSG";
	
	public static ResponseEntity<SearchResponse> handleSearchError(final String errorMessage) {
		ErrorResponse err = new ErrorResponse(errorMessage);
		err.setMessage(errorMessage);
		SearchResponse rs = new SearchResponse();
		rs.error = err;
		return new ResponseEntity<SearchResponse>(rs, HttpStatus.BAD_REQUEST);
	}
	
	public static ResponseEntity<DetailResponse> handleDetailError(final String errorMessage) {
		ErrorResponse err = new ErrorResponse(errorMessage);
		DetailResponse rs = new DetailResponse();
		rs.error = err;
		return new ResponseEntity<DetailResponse>(rs, HttpStatus.BAD_REQUEST);
	}
	
}
