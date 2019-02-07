package com.jlau78.foursquare.request;


import lombok.Data;
import lombok.NonNull;

/**
 * Places API Venue queries
 */
@Data
public class VenueRequest extends BaseRequest {

	@NonNull
	private String near;
	private String query;
	private String section;
	private String radius;
	private String limit;
	private String intent;
		
	public VenueRequest(final String near) {
		this.near = near;
	}

}
