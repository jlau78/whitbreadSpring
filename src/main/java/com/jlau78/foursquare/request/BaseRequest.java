package com.jlau78.foursquare.request;

import lombok.Data;
import lombok.NonNull;

/**
 * Foursquare base request holder
 */
@Data
public class BaseRequest {

	private String clientId;
	private String clientSecret;
	private String version;

}
