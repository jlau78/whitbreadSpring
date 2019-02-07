package com.jlau78.foursquare.request;

import lombok.Data;

/**
 * Foursquare base request holder
 */
@Data
public class BaseRequest {

	private String clientId;
	private String clientSecret;
	private String version;

}
