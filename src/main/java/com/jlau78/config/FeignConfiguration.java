package com.jlau78.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class FeignConfiguration {

	@Value("${feign.logLevel}")
	private String logLevel;

	@Bean
	public Logger.Level feignLoggerLevel() {
		return Arrays.stream(Logger.Level.values()).filter(level -> level.toString().equalsIgnoreCase(logLevel)).findFirst()
		    .orElse(Logger.Level.NONE);
	}

}
