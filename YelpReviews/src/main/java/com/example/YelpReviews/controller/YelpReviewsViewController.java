package com.example.YelpReviews.controller;

import com.example.YelpReviews.models.YelpReview;
import com.example.YelpReviews.services.YelpAPIService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class YelpReviewsViewController {
    private final YelpAPIService apiService;

    public YelpReviewsViewController(YelpAPIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping(path="/reviews")
    public String getReviews(@RequestParam(name="name") String name,
                             @RequestParam(name="location") String location,
                             Model model) {
        List<YelpReview> reviews = apiService.getReviews(name, location);
        model.addAttribute("restaurant", name);
        model.addAttribute("reviews", reviews);
        return "reviews";
    }

    @GetMapping(path="/greeting")
    public String getGreeting(@RequestParam(name="name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        System.out.println(name + " parameter is missing");
        // Actual exception handling
        return "index";
    }
}
