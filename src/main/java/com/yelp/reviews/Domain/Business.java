package com.yelp.reviews.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Business implements Serializable {
    String id;
    String alias;
    String name;
    String image_url;
    String is_closed;
    String url;
    int review_count;
    List<Category> categories;
    int rating;
    Coordinates coordinates;
    List<String> transactions;
    String price;
    Location location;
    String phone;
    String display_phone;
    int distance;


}
