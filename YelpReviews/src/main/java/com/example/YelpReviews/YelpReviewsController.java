package com.example.YelpReviews;

import com.example.YelpReviews.models.Business;
import com.example.YelpReviews.models.ReviewResponse;
import com.example.YelpReviews.models.SearchResponse;
import com.example.YelpReviews.models.YelpReview;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class YelpReviewsController {
    // api keys/info
    private static final String CLIENT_ID = "EGRrx2rXsISzXbGgS95GlQ";
    private static final String API_KEY = "GENAEkQi0DP0EOeMrN48TDAV09uYRNsqA5FuOcKmAj4rt2aLuN8JlnxQh6pj2tEEH674Inz0_DYBui1xlKydFzDJ14MgLbylGU59FA95tkIgfEMCJMQEyMCxObL4YXYx";
    // search requires a term, which can be a business name, and a location or latitude/longitude
    private static final String API_SEARCH_ENDPOINT = "https://api.yelp.com/v3/businesses/search";
    // full URL: https://api.yelp.com/v3/businesses/{id}/reviews, where {id} is the business ID returned from the search
    private static final String API_REVIEW_ENDPONT = "https://api.yelp.com/v3/businesses/";
    // default restaurant info
    // you can pass any restaurant name and address to get the relevant results via query string
    private static final String MY_RESTAURANT = "Pho One24";
    private static final String RESTAURANT_ADDR = "3705 N 124th St Ste 400 Brookfield, WI 53005";

    @RequestMapping(value="/greeting", method= RequestMethod.GET)
    public String greeting(@RequestParam(name="name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping(value="/reviews", method= RequestMethod.GET)
    public String reviews(@RequestParam(name="name", required = false, defaultValue = MY_RESTAURANT) String name,
                          @RequestParam(name="location", required = false, defaultValue = RESTAURANT_ADDR) String location,
                          Model model){
        // Set up the headers with the proper API key so that I can access the API
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + API_KEY);
        headers.add("Content-Type", "text/JSON");
        HttpEntity entity = new HttpEntity("body", headers);

        // Use the search endpoint to find the ID of my restaurant
        String url = API_SEARCH_ENDPOINT +
                "?term=" + URLEncoder.encode(name, StandardCharsets.UTF_8) +
                "&location=" + URLEncoder.encode(location, StandardCharsets.UTF_8);
//        System.out.println(url);
        RestTemplate template = new RestTemplate();
        ResponseEntity<SearchResponse> response = template.exchange(url, HttpMethod.GET, entity, SearchResponse.class);
//        System.out.println(response.toString());

        // parse out the list of businesses in the response and get the ID
        // for the sake of this exercize, I know that the first result is the one I want, but I would
        // verify the results before continuing
        List<Business> businesses = response.getBody().getBusinesses();
        if(businesses != null && businesses.size() > 0) {
            Business myBusiness = businesses.get(0);
            String myId = myBusiness.getId();

            // get the reviews for the restaurant using the ID
            url = API_REVIEW_ENDPONT + myId + "/reviews";
            System.out.println(url);
            template = new RestTemplate();
            ResponseEntity<ReviewResponse> reviews = template.exchange(url, HttpMethod.GET, entity, ReviewResponse.class);
            List<YelpReview> myReviews = reviews.getBody().getReviews();
            Map<String, List<YelpReview>> allReviews = new HashMap<>();
            allReviews.put("reviews", myReviews);
            // update model with my reviews
            model.addAttribute("myReviews", myReviews);

            // convert to JSON output
            String jsonOutput = "";
            ObjectMapper mapper = new ObjectMapper();
            try {
                // pretty print the JSON to console
                jsonOutput = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(allReviews);
                System.out.println(jsonOutput);
            } catch (IOException e) {
                // print stack trace and continue
                e.printStackTrace();
            }
        }

        // update model with my restaurant name
        model.addAttribute("myRestaurant", MY_RESTAURANT);

        return "reviews";
    }
}
