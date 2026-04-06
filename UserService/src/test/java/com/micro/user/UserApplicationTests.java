package com.micro.user;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.micro.user.externalservice.RatingService;

@SpringBootTest
class UserApplicationTests {
	
	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void createRating() {
		 Map<String, Object> rating = new HashMap<>();
	        rating.put("userId", "u1");
	        rating.put("hotelId", "h1");
	        rating.put("score", 5);
	        rating.put("feedback", "Excellent service");

	        // Call Feign client
	        Map<String, Object> response = ratingService.createRating(rating);

	        // Print response
	        System.out.println(response);

		//ratingService.createRating(rating);
	}

}
