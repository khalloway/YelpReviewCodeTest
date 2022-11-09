package com.example.YelpReviews.controller;

import com.example.YelpReviews.models.YelpReview;
import com.example.YelpReviews.services.YelpAPIService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class YelpReviewsRestController {

    private final YelpAPIService apiService;

    public YelpReviewsRestController(YelpAPIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping(path="/api/reviews", produces = {"application/json"})
    public List<YelpReview> getReviews(@RequestParam(name="name") String name,
                          @RequestParam(name="location") String location){
        return apiService.getReviews(name, location);
    }


}
