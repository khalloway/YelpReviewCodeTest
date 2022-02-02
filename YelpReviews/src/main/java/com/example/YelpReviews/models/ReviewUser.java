package com.example.YelpReviews.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewUser {
    private String id;
    private String profile_url;
    private String image_url;
    private String name;

    public ReviewUser() {}

    public void setId(String id) {
        this.id = id;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ReviewUser{" +
                "id='" + id + '\'' +
                ", profile_url='" + profile_url + '\'' +
                ", image_url='" + image_url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
