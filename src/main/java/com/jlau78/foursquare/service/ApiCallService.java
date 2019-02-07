package com.jlau78.foursquare.service;

import com.jlau78.common.exceptions.AppException;

/**
 * Call Service component for the Foursquare API Service
 */
public interface ApiCallService<R, Q> {

	/**
	 * Name of this ApiCallService
	 */
	public String getName();

	/**
	 * Call the Api
	 * 
	 * @param request
	 *          Input request
	 * @return Response from Api call
	 * @throws AppException
	 */
	public R call(final Q request) throws AppException;

	default void init() {
		System.out.println("Starting ApiCallService:" + this.getClass().getName());

	}

}
