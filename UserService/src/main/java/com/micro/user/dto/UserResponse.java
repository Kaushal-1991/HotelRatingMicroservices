package com.micro.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.micro.user.model.Rating;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	private String id;
	private String name;
	private String email;
	private String description;
	private List<Rating> ratings = new ArrayList<>();
}
