package com.jlau78.common.exceptions;

public class AppException extends Exception {

	private static final long serialVersionUID = 3328140657194355324L;

	public AppException(final String message) {
		super(message);
	}

	public AppException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
