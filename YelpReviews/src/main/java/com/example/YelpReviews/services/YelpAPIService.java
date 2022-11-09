package com.example.YelpReviews.services;

import com.example.YelpReviews.models.Business;
import com.example.YelpReviews.models.ReviewResponse;
import com.example.YelpReviews.models.SearchResponse;
import com.example.YelpReviews.models.YelpReview;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YelpAPIService {
    // api keys/info
    private static final String CLIENT_ID = "EGRrx2rXsISzXbGgS95GlQ";
    private static final String API_KEY = "GENAEkQi0DP0EOeMrN48TDAV09uYRNsqA5FuOcKmAj4rt2aLuN8JlnxQh6pj2tEEH674Inz0_DYBui1xlKydFzDJ14MgLbylGU59FA95tkIgfEMCJMQEyMCxObL4YXYx";
    // search requires a term, which can be a business name, and a location or latitude/longitude
    private static final String API_URL = "https://api.yelp.com/v3";

    // default restaurant info
    // you can pass any restaurant name and address to get the relevant results via query string
    private static final String MY_RESTAURANT = "Pho One24";
    private static final String RESTAURANT_ADDR = "3705 N 124th St Ste 400 Brookfield, WI 53005";

    private HttpEntity createHttpEntity() {
        // Set up the headers with the proper API key so that I can access the API
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + API_KEY);
        headers.add("Content-Type", "text/JSON");
        return new HttpEntity("body", headers);
    }

    private List<Business> searchBusiness(String name, String address) {
        String searchUrl = API_URL + "/businesses/search";

        // Use the search endpoint to find the ID of my restaurant
        String url = searchUrl +
                "?term=" + URLEncoder.encode(name, StandardCharsets.UTF_8) +
                "&location=" + URLEncoder.encode(address, StandardCharsets.UTF_8);
        RestTemplate template = new RestTemplate();
        ResponseEntity<SearchResponse> response = template.exchange(url, HttpMethod.GET, createHttpEntity(), SearchResponse.class);

        return response.getBody().getBusinesses();
    }

    private List<YelpReview> searchReviews(String businessID) {
        // get the reviews for the restaurant using the ID
        String url = API_URL + "/businesses/" + businessID + "/reviews";
        RestTemplate template = new RestTemplate();
        ResponseEntity<ReviewResponse> reviews = template.exchange(url, HttpMethod.GET, createHttpEntity(), ReviewResponse.class);
        return reviews.getBody().getReviews();
    }

    public List<YelpReview> getReviews(String name, String address) {
        List<YelpReview> myReviews = new ArrayList<>();
        if(name.isEmpty() || address.isEmpty()) {
            // return empty list
            System.out.println("missing params");
            return myReviews;
        }

        List<Business> businesses = searchBusiness(name, address);

        if(businesses != null && businesses.size() > 0) {
            Business myBusiness = businesses.get(0);
            String myId = myBusiness.getId();
            myReviews = searchReviews(myId);
        }
        return myReviews;
    }
}
