package com.jlau78.foursquare.service;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jlau78.common.exceptions.AppException;
import com.jlau78.foursquare.client.PlacesApiClient;
import com.jlau78.foursquare.request.VenueRequest;
import com.jlau78.foursquare.response.venue.SearchResponse;
import com.jlau78.foursquare.response.venue.VenueSearchRS;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class VenueRecommendationCallTest {
	@Tested
	VenueRecommendationCall apiCall;

	@Injectable
	PlacesApiClient apiClient;

	VenueRequest request1 = new VenueRequest("rome");
	VenueSearchRS response1 = new VenueSearchRS();

	@Before
	public void setUp() throws Exception {

		request1.setClientId("111111");
		request1.setClientSecret("secretkey");
		request1.setVersion("20190222");
		response1.setResponse(new SearchResponse());

	}

	@Test
	public void testCallSuccess() throws AppException {
		new Expectations() {
			{
				apiClient.venueRecommendationsByName(anyString, anyString, anyString, anyString, anyString, anyString, anyString, anyString,
				    anyString);
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
