package com.yelp.reviews.Domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Businesses implements Serializable {
    List<Business> businesses;
}
