package com.jlau78.foursquare.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jlau78.common.exceptions.AppException;
import com.jlau78.foursquare.client.PlacesApiClient;
import com.jlau78.foursquare.request.VenueRequest;
import com.jlau78.foursquare.response.Response;
import com.jlau78.foursquare.response.VenueSearchRS;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class VenueSearchCallTest {

	@Tested
	VenueSearchCall apiCall;

	@Injectable
	PlacesApiClient apiClient;

	@Injectable
	String apiVersion;

	@Injectable
	String clientId;

	@Injectable
	String clientSecret;

	VenueRequest request1 = new VenueRequest("rome");
	VenueSearchRS response1 = new VenueSearchRS();

	@Before
	public void setUp() throws Exception {

		request1.setClientId("111111");
		request1.setClientSecret("secretkey");
		request1.setVersion("20190222");
		response1.setResponse(new Response());

	}

	@Test
	public void testCallSuccess() throws AppException {
		new Expectations() {
			{
				apiClient.venueSearchByName(anyString, anyString, anyString, anyString, anyString, anyString);
				result = response1;
			}
		};

		VenueSearchRS r = apiCall.call(request1);

		assertTrue(r == response1);
	}

	@Test
	public void testCallNullRequest() throws AppException {
		VenueSearchRS r = apiCall.call(null);

		assertNull(r);
	}

}
