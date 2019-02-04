package com.jlau78.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;

import com.jlau78.common.exceptions.ErrorResponse;
import com.jlau78.foursquare.request.VenueRequest;
import com.jlau78.foursquare.response.venue.SearchResponse;
import com.jlau78.foursquare.response.venue.Venue;
import com.jlau78.foursquare.response.venue.VenueSearchRS;
import com.jlau78.foursquare.service.VenueSearchCall;
import com.jlau78.handler.VenueDetailsMulitQueryHandler;
import com.jlau78.handler.VenueSearchCallHandler;

import feign.FeignException;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class VenueControllerTest {

	@Tested
	VenueController testComponent;

	@Injectable
	VenueSearchCall callService;

	@Injectable
	VenueDetailsMulitQueryHandler venueDetailsHandler;

	@Injectable
	VenueSearchCallHandler venueSearchCallHandler;

	@Mocked
	FeignException feignException;

	@Mocked
	ResponseEntity responseError1;

	@Mocked
	VenueSearchRS vSearchRS1;

	VenueSearchRS vResponse = new VenueSearchRS();
	SearchResponse response1 = new SearchResponse();

	@Mocked
	ErrorResponse errorResp1;

	@Mocked
	ResponseEntity<SearchResponse> respEntity1;

	@Mocked
	VenueSearchRS vSearchRSWithError;

	@Mocked
	SearchResponse searchRespWithError;

	@Mocked
	SearchResponse searchResp1;

	@Before
	public void setUp() throws Exception {

		response1.venues = new ArrayList<Venue>();

	}

	@Test
	public void testVenueSearchSuccess() {

		new Expectations() {
			{
				vSearchRS1.getResponse();
				result = searchResp1;

				testComponent.getVenueSearchCallHandler().handle((VenueRequest) any);
				result = vSearchRS1;
			}
		};

		ResponseEntity<SearchResponse> r = testComponent.getVenueByLocationName("paris", "cafe");

		assertNotNull(r);
	}

	@Test
	public void testVenueSearchErrorResponse() {

		new Expectations() {
			{
				vSearchRSWithError.getResponse();
				result = searchRespWithError;

				testComponent.getVenueSearchCallHandler().handle((VenueRequest) any);
				result = vSearchRSWithError;
			}
		};

		ResponseEntity<SearchResponse> r = testComponent.getVenueByLocationName("paris", "cafe");

		assertNotNull(r);
	}

	// @Test
	// public void testSearchErrorResponse() {
	//
	// new Expectations() {
	// {
	//
	// errorResp1.getMessage();
	// result = "AppException thrown by call";
	// venueSearchRSWithError.getResponse();
	// result = searchRespWithError;
	// searchRespWithError.getError();
	// result = errorResp1;
	// // ExceptionHandler.handleSearchError(anyString);
	// // result = responseError1;
	//
	// testComponent.getVenueSearchCallHandler().handle((VenueRequest) any);
	// result = venueSearchRSWithError;
	// }
	// };
	//
	// ResponseEntity resp = testComponent.getVenueByLocationName("rome", "");
	//
	// assertNotNull(resp);
	//
	// };
	//
}
