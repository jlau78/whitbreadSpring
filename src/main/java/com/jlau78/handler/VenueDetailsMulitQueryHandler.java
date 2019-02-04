package com.jlau78.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlau78.common.exceptions.AppException;
import com.jlau78.common.exceptions.ErrorResponse;
import com.jlau78.foursquare.request.VenueDetailsRequest;
import com.jlau78.foursquare.response.venue.DetailResponse;
import com.jlau78.foursquare.response.venue.VenueDetailRS;
import com.jlau78.foursquare.service.VenueDetailsCall;

import feign.FeignException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Handler to process a list of venueIds and makes calls to the api
 */
@Slf4j
@Service
public class VenueDetailsMulitQueryHandler implements Handler<List<String>, List<VenueDetailRS>> {
	
	private static boolean demoAllowErrors = true;
	
	@Getter
	@Autowired
	VenueDetailsCall apiCall;

	@Override
	public List<VenueDetailRS> handle(List<String> inputs) {
		List<VenueDetailRS> details = new ArrayList<>();

		try {
			if (inputs != null) {
				if (demoAllowErrors) {
					details = getAllVenueDetails(inputs);
				} else {
				details = getAllVenueDetails(inputs).stream()
				    .filter(d -> d.getResponse().error == null)
				    .collect(Collectors.toList());
				}
			}
		} catch (Exception e) {
			log.error("FATAL: Failure to get all the venue details for ids:{}", inputs);
		}
		return details;
	}
	
	protected List<VenueDetailRS> getAllVenueDetails(final List<String> venueIds) throws InterruptedException, ExecutionException {
		List<VenueDetailRS> venueDetails = null;

		if (CollectionUtils.isNotEmpty(venueIds)) {
			log.info("Start query for the list of venueIds: {}", venueIds);

			ExecutorService executor = Executors.newFixedThreadPool(2);
			try {
				List<CompletableFuture<VenueDetailRS>> futures = venueIds.stream().map(k -> {
					return CompletableFuture.supplyAsync(() -> getDetail(k), executor);
				}).collect(Collectors.toList());

				venueDetails = 
					(List<VenueDetailRS>) CompletableFuture
									.allOf(futures.toArray(new CompletableFuture[futures.size()]))
									.thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()))
									.get();
				
			} finally {
				executor.shutdownNow();
			}
		}
		
		return venueDetails;
	}
	
	private VenueDetailRS getDetail(final String venueId) {
		String errorMsg = "";
    VenueDetailRS rs = null;
    try {
    	log.info("Calling Foursquare API for venueDetails information. venueId:{}", venueId);
      VenueDetailsRequest rq = new VenueDetailsRequest(venueId);
      rs = getApiCall().call(rq);

    } catch (FeignException e) {
      log.error("FeignException calling to the foursquare api: {}", e.getMessage());
      errorMsg = e.getMessage();
    } catch (AppException e) {
      log.error("AppException performing a Venue Search query:{}", e.getMessage());
      errorMsg = e.getMessage();
    } 

    if (StringUtils.isNotEmpty(errorMsg)) {
    	rs = new VenueDetailRS();
    	DetailResponse detailResponse = new DetailResponse();
    	detailResponse.error =  new ErrorResponse(errorMsg);
    	rs.setResponse(detailResponse);
    }
    
    return rs;
	}

}
