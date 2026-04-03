package com.micro.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.rating.dto.RatingRequest;
import com.micro.rating.dto.RatingResponse;
import com.micro.rating.services.RatingService;


@RestController
@RequestMapping("/api/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@PostMapping
	public ResponseEntity<RatingResponse> createRating(@RequestBody RatingRequest request){
		RatingResponse response = ratingService.saveRating(request);
		return new ResponseEntity<RatingResponse>(response,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<RatingResponse>> getAllRating(){
		List<RatingResponse> response = ratingService.getAllRating();
		return  ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<RatingResponse>> getRatingByHotelId(@PathVariable(name="hotelId") String hotelId){
		List<RatingResponse> response = ratingService.getRatingByHotelId(hotelId);
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<RatingResponse>> getRatingByUserId(@PathVariable(name="userId") String userId){
		List<RatingResponse> response = ratingService.getRatingByUserId(userId);
		return ResponseEntity.ok().body(response);
	}
	
	
}
