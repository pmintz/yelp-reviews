package com.yelp.reviews.Domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Location implements Serializable {
    String address1;
    String address2;
    String address3;
    String city;
    String zip_code;
    String country;
    String state;
    List<String> display_address;
}
