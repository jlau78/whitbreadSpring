# Whitbread Premier Inn Code Test Java 11.2 SpringBoot 2.1.2 Jan 2019

Author: James Lau
jameslauwf@gmail.com

## Contents
<!-- MarkdownTOC -->

- Requirements
- Git Repository
- Design
  - VenueController Responses
  - Spring Cloud library and reducing boilerplate code
    - Lombok
    - Netflix Feign
  - Json To Java: jsonschema2pojo
- Steps
  - Build
  - Running the Application
    - Shell startup scripts
- Issues
  - Slf4j library conflicts preventing Debug level statements

<!-- /MarkdownTOC -->


## Requirements 

You are tasked with creating a microservice which allows you to search for a place by name and return the recommended or popular venues near that location, using the Foursquare API and returning the results in JSON format. 


## Git Repository

[GitHub jlau78/whitbreadSpring Repository](https://github.com/jlau78/whitbreadSpring)


## Design

The Demo application makes calls to the Foursquare API for Venues for a given location. Another call is made for multiple venues detail information as well.

To access the Demo application go to the following URLs:

- [Swagger UI for Demo Application localhost:8085](http://localhost:8085/swagger-ui.html)
- [Sample London cafe Venue query /venue/search](http://localhost:8085/venue/search?venue=london&query=cafe)
- [Sample London museum Venue query and multi details /venue/search](http://localhost:8085/venue/searchAndDetail?venue=London&query=museum)


There is a single RestController in this application that exposes the Rest methods above. This controller calls the ApiCallService implementations that manages calls to the Foursquare API for information. The Foursquare API call implementation component is handled by a single Feign client, PlacesApiClient.

The response Json returned from the Foursquare API is directly mapped to POJOs without modification to the schema. Json class beans were generated online from [jsonschema2pojo](http://www.jsonschema2pojo.org/). It would have been good to map these 'DTO' POJOs to the application specific schema POJOs for future enrichment or persistence. However, attempts at fixing compatibility between my chosen POJO frameworks [MapStruct](http://mapstruct.org) and [Lombok](https://projectlombok.org) were not successfull and hence the current design.

As the Foursquare API already provided filtering of results via a 'query' param, I decided the best way to demonstrate Java 8 features, specifically Lambda, was to provide a component that makes multiple calls for venue details. See [Search and multiple details Rest method](http://localhost:8085/venue/searchAndDetail?venue=London&query=museum)

(It was noticed later that there is a limited quota of these venue details calls allowable. So, the component will still return errors for each call to demonstrate it is working)

### VenueController Responses

The application will return HTTP Response code OK (200) and BAD_REQUEST (400). 

Where there is an error making the Foursquare API call, the error from the API call will be propagated as is and returned as-is in the Response Json.

**Notes**
The server.port is set to 8085 to avoid port conflicts when starting Tomcat. This is done in case developers have an application already running on the default port 8080.

### Spring Cloud library and reducing boilerplate code

#### Lombok

The [Lombok libraries](https://projectlombok.org/) are used to reduce the need to creating boilerplate getter and setter code. I expect you are using the Lombok libraries but if not here is a short summary to get started.

The Lombok libraries will generate the required getters and setters at compile time. However to allow your IDE to link to them you will need to enable Lombok support in your IDE. For the Eclipse IDE which I use, refer to [Setup Lombok for Eclipse](https://projectlombok.org/setup/eclipse) for instructions on how to install the Lombok feature.

#### Netflix Feign

The Netflix Feign libraries are used for making calls to the Foursquare Rest APIs. Such calls are not boilerplate code, but these libraries reduces any 'plumbing' code required to manage such remote calls. 

Other Cloud features are not implemented in this demo, but the use of the Feign libraries works nicely with features like the Hystrix circuit breaker and Ribbon load balancing libraries among others. Hence the choice to use it for this feature.

### Json To Java: jsonschema2pojo 

The site [jsonschema2pojo](http://www.jsonschema2pojo.org/) was used to generated the Java Jackson 2 beans code for the Response POJOs.

## Steps

### Build

The application is built using Maven. I have included the built Snapshot Jar in the target directory so you can run the application without having to run a build. 

**My Code build specs**

Maven home: C:\apps\apache-maven-3.5.2
Java version: 1.8.0_131, vendor: Oracle Corporation


### Running the Application

#### Shell startup scripts

You can run the application directly with the provide scripts for convenience:

Linux/Cygwin:   startBootApp.sh

## Issues

### Slf4j library conflicts preventing Debug level statements

The spring-boot-starter dependency has a sl4j class causing class binding issues with the apache log4j loaded by another dependency. This prevents debug level logging.

This could be overcome in the Eclipse IDE by manually removing the offending logback-classic and logback-core libraries. This allows verbose wire logging of the Feign calls to the Foursquare API, provided by the configuration: feign.logLevel=Full

It is not possible to fix this in the time scale.

