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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "address",
    "lat",
    "lng",
    "labeledLatLngs",
    "postalCode",
    "cc",
    "city",
    "state",
    "country",
    "formattedAddress"
})
public class Location_ {

	@JsonProperty("address")
	public String address;
	@JsonProperty("lat")
	public Double lat;
	@JsonProperty("lng")
	public Double lng;
	@JsonProperty("labeledLatLngs")
	public List<LabeledLatLng> labeledLatLngs = null;
	@JsonProperty("postalCode")
	public String postalCode;
	@JsonProperty("cc")
	public String cc;
	@JsonProperty("city")
	public String city;
	@JsonProperty("state")
	public String state;
	@JsonProperty("country")
	public String country;
	@JsonProperty("formattedAddress")
	public List<String> formattedAddress = null;
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

}