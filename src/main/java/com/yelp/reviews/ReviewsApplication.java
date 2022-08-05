package com.yelp.reviews;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ReviewsApplication {

	@Value("${yelp.bearer.token}")
	private String yelpBearerToken;

	@Value("${yelp.base.url}")
	private String yelpBaseUrl;

	public static void main(String[] args) {

		SpringApplication.run(ReviewsApplication.class, args);

	}

	@Bean
	public RestTemplate collectCentRestTemplate(RestTemplateBuilder builder) {
		return builder.rootUri(yelpBaseUrl)
				.additionalInterceptors((ClientHttpRequestInterceptor) (request, body, execution) -> {
					request.getHeaders()
							.add("Authorization", "Bearer " + yelpBearerToken);
					return execution.execute(request, body);
				}).build();
	}




}

