package com.jlau78.handler;

public interface Handler<T, U> {

	public U handle(final T input);
}
