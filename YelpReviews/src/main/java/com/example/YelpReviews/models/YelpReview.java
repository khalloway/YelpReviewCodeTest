package com.example.YelpReviews.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YelpReview {
    private String id;
    private int rating;
    private String text;
    private String time_created;
    private String url;
    private ReviewUser user;

    public YelpReview() {}

    public void setId(String id) {
        this.id = id;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime_created(String time_created) {
        this.time_created = time_created;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(ReviewUser user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }

    public String getTime_created() {
        return time_created;
    }

    public String getUrl() {
        return url;
    }

    public ReviewUser getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "YelpReview{" +
                "id='" + id + '\'' +
                ", rating=" + rating +
                ", text='" + text + '\'' +
                ", time_created='" + time_created + '\'' +
                ", url='" + url + '\'' +
                ", user=" + user +
                '}';
    }
}
