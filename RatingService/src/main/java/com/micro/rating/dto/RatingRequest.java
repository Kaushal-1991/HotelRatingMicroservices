package com.micro.rating.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequest {
	private String userId;
	private String hotelId;
	private int rating;
	private String feedback;
}
