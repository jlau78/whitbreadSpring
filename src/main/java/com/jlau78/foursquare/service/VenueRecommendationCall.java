package com.jlau78.foursquare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.jlau78.common.exceptions.AppException;
import com.jlau78.foursquare.client.PlacesApiClient;
import com.jlau78.foursquare.request.VenueRequest;
import com.jlau78.foursquare.response.VenueSearchRS;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class VenueRecommendationCall implements ApiCallService<VenueSearchRS, VenueRequest> {

	@Getter
	@Autowired
	PlacesApiClient apiClient;
	
	@Value("${service.foursquare.api.api_version}")
	String apiVersion = "20190122";

	@Value("${service.foursquare.api.client_id}")
	String clientId = "";

	@Value("${service.foursquare.api.client_secret}")
	String clientSecret = "";



	@Override
	public VenueSearchRS call(VenueRequest request) throws AppException {
		VenueSearchRS response = null;

		if (request != null) {
			response = getApiClient().venueRecommendationsByName(request.getQuery(), request.getNear(), request.getSection(), request.getRadius(), 
																request.getIntent(), apiVersion, clientId, clientSecret);
		}
		return response;

	}

}
