package com.micro.rating.serviceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rating.dto.RatingRequest;
import com.micro.rating.dto.RatingResponse;
import com.micro.rating.exception.ResourceNotFoundException;
import com.micro.rating.model.Rating;
import com.micro.rating.repository.RatingRepository;
import com.micro.rating.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public RatingResponse saveRating(RatingRequest ratingRequest) {
		Rating rating = new Rating();
		String ratingId = UUID.randomUUID().toString();
		rating.setRatingId(ratingId);
		mapDtoToRating(rating,ratingRequest);
		ratingRepository.save(rating);
		return mapUserToDto(rating);
	}

	
	@Override
	public List<RatingResponse> getAllRating() {
		return ratingRepository.findAll().stream().map(this::mapUserToDto).collect(Collectors.toList());
	}

	@Override
	public List<RatingResponse> getRatingByUserId(String userId) {
		List<Rating> findByUserId = ratingRepository.findByUserId(userId);
		if(findByUserId.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		findByUserId.stream().map(this::mapUserToDto).toList();
		return findByUserId.stream().map(this::mapUserToDto).toList();
	}

	@Override
	public List<RatingResponse> getRatingByHotelId(String hotelId) {
		List<Rating> findByHotelId = ratingRepository.findByHotelId(hotelId);
		if(findByHotelId.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return findByHotelId.stream().map(this::mapUserToDto).toList();
	}
	
	private RatingResponse mapUserToDto(Rating rating) {
		RatingResponse response = new RatingResponse();
		response.setRatingId(rating.getRatingId());
		response.setHotelId(rating.getHotelId());
		response.setUserId(rating.getUserId());
		response.setRating(rating.getRating());
		response.setFeedback(rating.getFeedback());
		
		return response;
	}

	private void mapDtoToRating(Rating rating, RatingRequest ratingRequest) {
		rating.setHotelId(ratingRequest.getHotelId());
		rating.setUserId(ratingRequest.getUserId());
		rating.setFeedback(ratingRequest.getFeedback());
		rating.setRating(ratingRequest.getRating());
		
	}


}
