package com.yelp.reviews.Domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Coordinates implements Serializable {
    int latitude;
    int longitude;
}
