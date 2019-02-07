package com.jlau78.common.exceptions;

import lombok.Data;

/**
 * Error response object to be returned to the client
 */
@Data
public class ErrorResponse {

	private String message;

	public ErrorResponse(final String message) {
		this.message = message;
	}

}
