package com.yelp.reviews.Domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Category implements Serializable {
    String alias;
    String title;
}
