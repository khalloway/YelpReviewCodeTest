package com.example.YelpReviews.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponse {
    private int totalResponses;
    private List<Business> businesses;

    public SearchResponse(){}

    public int getTotalResponses() {
        return totalResponses;
    }

    public void setTotalResponses(int totalResponses) {
        this.totalResponses = totalResponses;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "totalResponses=" + totalResponses +
                ", businesses=" + businesses +
                '}';
    }
}
