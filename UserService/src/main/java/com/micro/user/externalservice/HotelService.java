package com.micro.user.externalservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.micro.user.model.Hotel;

@FeignClient(name="HOTEL-SERVCIE")
public interface HotelService {

	@GetMapping("/api/hotels/{hotelId}")
	Hotel getHotel(@PathVariable String hotelId);
}
