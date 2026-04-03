package com.micro.rating.services;

import java.util.List;

import com.micro.rating.dto.RatingRequest;
import com.micro.rating.dto.RatingResponse;

public interface RatingService {

	RatingResponse saveRating(RatingRequest ratingRequest);
	
	List<RatingResponse> getAllRating();
	
	List<RatingResponse> getRatingByUserId(String userId);
	
	List<RatingResponse> getRatingByHotelId(String hotelId);
	
	//RatingResponse updateRating(String ratingId,RatingRequest ratingRequest);
	
	//Map<String, Object> deleteRating(String ratingId);
}
