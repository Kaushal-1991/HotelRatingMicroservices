package com.micro.user.externalservice;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.micro.user.model.Rating;

@Service
@FeignClient(name="RATING-SERVICE")
public interface RatingService {
   
	@PostMapping("/api/ratings")
	public Map<String, Object> createRating(Map<String, Object> rating);
}
