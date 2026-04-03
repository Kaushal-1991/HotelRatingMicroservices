package com.micro.user.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micro.user.dto.UserRequest;
import com.micro.user.dto.UserResponse;
import com.micro.user.exception.ResourceNotFoundException;
import com.micro.user.externalclient.HotelService;
import com.micro.user.model.Hotel;
import com.micro.user.model.Rating;
import com.micro.user.model.User;
import com.micro.user.repository.UserRepository;
import com.micro.user.services.UserServie;

@Service
public class UserServiceImpl implements UserServie {
	
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;

	@Override
	public UserResponse saveUser(UserRequest request) {
		User user = new User();
		String userId = UUID.randomUUID().toString();
		user.setId(userId);
		mapDtoToUser(user, request);
		userRepository.save(user);
		return mapUserToUserDto(user);
	}

	@Override
	public List<UserResponse> getAllUser() {
		return userRepository.findAll()
				             .stream()
				             .map(this::mapUserToUserDto) // map(usr -> mapUserToUserDto(usr))
				             .collect(Collectors.toList());
	}

	@Override
	public UserResponse getUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not found with  this id"));
		
		//Get Ratings 
		ResponseEntity<List<Rating>> response = restTemplate.exchange("http://RATING-SERVICE/api/ratings/user/" + user.getId(),
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<List<Rating>> () {});
		List<Rating> ratingList = response.getBody();
		
		logger.debug("{} ", ratingList);
		
		List<Rating> hotelRating = ratingList.stream().map(rating -> {
//			ResponseEntity<Map<String, Object>> hotelResponse = restTemplate.exchange("http://HOTEL-SERVCIE/api/hotels/" + rating.getHotelId(),
//					HttpMethod.GET, 
//					null,
//					new ParameterizedTypeReference<Map<String, Object>> () {});
//			Map<String,Object> responseHotel = hotelResponse.getBody();
//			
//			logger.info("Hotel Response is : {} "+responseHotel);
//			
//			Hotel hotel = new Hotel();
//			if(responseHotel != null ) {
//				hotel.setId((String)responseHotel.get("id"));
//				hotel.setAbout((String) responseHotel.get("about"));
//				hotel.setName((String) responseHotel.get("name"));
//				hotel.setLocation((String) responseHotel.get("location"));
//			}
//			
//			rating.setHotel(hotel);
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);

	        return rating;
		}).collect(Collectors.toList());
		
		UserResponse mapUserToUserDto = mapUserToUserDto(user);
		mapUserToUserDto.setRatings(hotelRating);
		
		return mapUserToUserDto;
	}

	@Override
	public UserResponse updateUser(UserRequest request, String userId) {
		Optional<User> optUserId = userRepository.findById(userId);
		if(optUserId.isEmpty()) {
			throw new ResourceNotFoundException("User Not found with  this id");
		}
		User user = optUserId.get();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setDescription(request.getDescription());
		userRepository.save(user);
		return  mapUserToUserDto(user);
	}

	@Override
	public Map<String, Object> deleteUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not found with  this id"));
        userRepository.delete(user);
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("Msg", "User Deleted");
        mapResponse.put("status", true);
		return mapResponse;
	}

	private void mapDtoToUser(User user, UserRequest request) {
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setDescription(request.getDescription());
	}

	public UserResponse mapUserToUserDto(User user) {
		UserResponse response = new UserResponse();
		response.setId(user.getId());
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setDescription(user.getDescription());
		return response;
	}

}
