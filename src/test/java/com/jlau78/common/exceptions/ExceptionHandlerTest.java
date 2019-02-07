package com.jlau78.common.exceptions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jlau78.foursquare.response.venue.SearchResponse;

public class ExceptionHandlerTest {

	@Spy
	ExceptionHandler testComponent;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHandleSuccess() {

		ResponseEntity<SearchResponse> res =  ExceptionHandler.handleSearchError(new AppException("Feign error encountered").getMessage());

		assertTrue(HttpStatus.BAD_REQUEST.equals(res.getStatusCode()));
		assertTrue(res.getBody().getError().getMessage().contains("Feign"));
	}

}
