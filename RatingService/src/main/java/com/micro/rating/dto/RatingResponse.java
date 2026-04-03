package com.micro.rating.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse {
	private String ratingId;
	private String userId;
	private String hotelId;
	private int rating;
	private String feedback;
}
