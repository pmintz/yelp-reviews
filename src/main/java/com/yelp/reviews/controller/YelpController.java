package com.yelp.reviews.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yelp.reviews.service.YelpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YelpController {

    YelpService yelpService;

    public YelpController(YelpService yelpService){
        this.yelpService = yelpService;
    }


    @GetMapping("/yelp-reviews")
    public String yelpSearch(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "locale") String locale) throws JsonProcessingException {

       return yelpService.findBusiness(name, locale);

    }


}
