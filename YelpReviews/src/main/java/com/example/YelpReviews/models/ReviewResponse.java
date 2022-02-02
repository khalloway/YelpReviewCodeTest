package com.example.YelpReviews.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewResponse {
    private List<YelpReview> reviews;

    public ReviewResponse() {}

    public List<YelpReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<YelpReview> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "reviews=" + reviews.toString() +
                '}';
    }
}
