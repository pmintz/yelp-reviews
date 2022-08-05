package com.yelp.reviews.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yelp.reviews.Domain.Business;
import com.yelp.reviews.Domain.Businesses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YelpService {
    RestTemplate restTemplate;

    public YelpService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    /**
     * The method will first call https://api.yelp.com/v3/businesses/search to determine that
     * the business had been found and the retrieve it's ID which will be used in a subsequent
     * call to https://api.yelp.com/v3/businesses/{id}/reviews to retrieve the reviews for
     * that particular business
     *
     * @param name name of the business
     * @param locale  This string indicates the geographic area to be used when searching for businesses.
     *                Examples: "New York City", "NYC", "350 5th Ave, New York, NY 10118".
     *                Businesses returned in the response may not be strictly within the specified location
     * @throws JsonProcessingException
     */
    public String findBusiness(String name, String locale) throws JsonProcessingException {
        String id;

        Businesses businesses =
                restTemplate.getForObject("/v3/businesses/search?" + "name=" + name
                                + "&location=" + locale
                        , Businesses.class);

        if (businesses != null) {
            int distance = Integer.MAX_VALUE;
            Business closest = null;
            for (Business business : businesses.getBusinesses()) {
                int currentDistance = StringUtils.getLevenshteinDistance(business.getName(), name);
                if (currentDistance < distance) {
                    distance = currentDistance;
                    closest = business;
                }

            }
            int index = businesses.getBusinesses().indexOf(closest);
            return retrieveReviews(businesses.getBusinesses().get(index));
        }
        return "Not Found";
    }

    public String retrieveReviews(Business business){
        String id = business.getId();
        ResponseEntity<String> response =
                restTemplate.getForEntity("/v3/businesses/" + id
                                + "/reviews"
                        , String.class);
        return response.toString();
    }


}
