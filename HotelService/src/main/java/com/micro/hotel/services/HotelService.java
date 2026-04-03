package com.micro.hotel.services;

import java.util.List;
import java.util.Map;

import com.micro.hotel.dto.HotelRequest;
import com.micro.hotel.dto.HotelResponse;

public interface HotelService {
	
	HotelResponse saveHotel(HotelRequest hotelRequest);
	
	List<HotelResponse> getAllHotel();
	
	HotelResponse getHotel(String hotelId);
	
	HotelResponse updateHotel(String hotelId,HotelRequest hotelRequest);
	
	Map<String,Object> deleteHotel(String hoteld);
}
