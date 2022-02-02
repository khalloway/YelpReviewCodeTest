package com.example.YelpReviews.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Business {
    private int rating;
    private String price;
    private String phone;
    private String id;
    private String alias;
    private boolean isClosed;

    public Business() {}

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    @Override
    public String toString() {
        return "Business{" +
                "rating=" + rating +
                ", price='" + price + '\'' +
                ", phone='" + phone + '\'' +
                ", id='" + id + '\'' +
                ", alias='" + alias + '\'' +
                ", isClosed=" + isClosed +
                '}';
    }
}
