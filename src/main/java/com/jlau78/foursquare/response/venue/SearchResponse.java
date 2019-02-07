package com.jlau78.foursquare.response.venue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.jlau78.common.exceptions.ErrorResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "venues",
    "geocode"
})
public class SearchResponse implements Response {

	@JsonProperty("venues")
	public List<Venue> venues = null;
	@JsonProperty("geocode")
	public Geocode geocode;
	@JsonProperty("error")
	public ErrorResponse error;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	 @JsonAnyGetter
	 public Map<String, Object> getAdditionalProperties() {
	 return this.additionalProperties;
	 }
	
	 @JsonAnySetter
	 public void setAdditionalProperty(String name, Object value) {
	 this.additionalProperties.put(name, value);
	 }

	@Override
	public ErrorResponse getError() {
		return this.error;
	}

}