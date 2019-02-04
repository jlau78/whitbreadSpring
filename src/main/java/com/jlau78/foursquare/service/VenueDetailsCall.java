package com.jlau78.foursquare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.jlau78.common.exceptions.AppException;
import com.jlau78.foursquare.client.PlacesApiClient;
import com.jlau78.foursquare.request.VenueDetailsRequest;
import com.jlau78.foursquare.request.VenueRequest;
import com.jlau78.foursquare.response.venue.DetailResponse;
import com.jlau78.foursquare.response.venue.VenueDetailRS;
import com.jlau78.foursquare.response.venue.VenueSearchRS;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class VenueDetailsCall implements ApiCallService<VenueDetailRS, VenueDetailsRequest>  {

	@Getter
	@Autowired
	private PlacesApiClient apiClient;
	
	@Value("${service.foursquare.api.api_version:20190222}")
	String apiVersion;

	@Value("${service.foursquare.api.client_id:}")
	String clientId;

	@Value("${service.foursquare.api.client_secret:}")
	String clientSecret;
		
	@Override 
	public VenueDetailRS call(VenueDetailsRequest request) throws AppException {
		VenueDetailRS response = null;
		
		if (request != null) {
			response = getApiClient().venueDetails(request.getVenueId(), apiVersion, 
												clientId, clientSecret);
		}
		return response;
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

}
