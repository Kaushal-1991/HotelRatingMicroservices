package com.micro.hotel.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.hotel.dto.HotelRequest;
import com.micro.hotel.dto.HotelResponse;
import com.micro.hotel.services.HotelService;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@PostMapping
	public ResponseEntity<HotelResponse> createHotel(@RequestBody HotelRequest request){
		HotelResponse hotelResponse = hotelService.saveHotel(request);
		return new ResponseEntity<HotelResponse>(hotelResponse,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<HotelResponse>> getAllHotel(){
		List<HotelResponse> hotelResponse = hotelService.getAllHotel();
		return ResponseEntity.ok().body(hotelResponse);
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<HotelResponse> getHotel(@PathVariable(name = "hotelId") String hotelId){
		HotelResponse hotelResponse = hotelService.getHotel(hotelId);
		return ResponseEntity.ok().body(hotelResponse);
	}
	
	@PutMapping("/{hotelId}")
	public ResponseEntity<HotelResponse> updateHotel(@PathVariable(name = "hotelId") String hotelId,@RequestBody HotelRequest hotelRequest){
		HotelResponse hotelResponse = hotelService.updateHotel(hotelId, hotelRequest);
		return ResponseEntity.ok().body(hotelResponse);
	}
	
	@DeleteMapping("/{hotelId}")
	public ResponseEntity<Map<String, Object>> deleteHotel(@PathVariable(name = "hotelId") String hotelId){
		Map<String, Object> response = hotelService.deleteHotel(hotelId);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);		
	}
}
