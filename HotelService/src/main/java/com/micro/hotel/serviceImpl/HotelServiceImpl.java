package com.micro.hotel.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.hotel.dto.HotelRequest;
import com.micro.hotel.dto.HotelResponse;
import com.micro.hotel.exception.ResourceNotFoundException;
import com.micro.hotel.model.Hotel;
import com.micro.hotel.repository.HotelRepository;
import com.micro.hotel.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public HotelResponse saveHotel(HotelRequest hotelRequest) {
		Hotel hotel = new Hotel();
		String hotelId = UUID.randomUUID().toString();
		hotel.setId(hotelId);
		mapDtoToHotel(hotel,hotelRequest);
		hotelRepository.save(hotel);
		return mapHotelToDto(hotel);
	}

	@Override
	public List<HotelResponse> getAllHotel() {
		return hotelRepository.findAll()
							  .stream()
							  .map(this::mapHotelToDto)
							  .collect(Collectors.toList());
	}

	@Override
	public HotelResponse getHotel(String hotelId) {
		return hotelRepository.findById(hotelId)
				              .map(this::mapHotelToDto)
				              .orElseThrow(() -> new ResourceNotFoundException());
	}

	@Override
	public HotelResponse updateHotel(String hotelId, HotelRequest hotelRequest) {
		Optional<Hotel> optHotelId = hotelRepository.findById(hotelId);
		if(optHotelId.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		Hotel hotel = optHotelId.get();
		hotel.setName(hotelRequest.getName());
		hotel.setAbout(hotelRequest.getAbout());
		hotel.setLocation(hotelRequest.getLocation());
		hotelRepository.save(hotel);
		return mapHotelToDto(hotel);
	}

	@Override
	public Map<String, Object> deleteHotel(String hotelId) {
		Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException());
		hotelRepository.delete(hotel);
		Map<String, Object> response = new HashMap<>();
		response.put("msg", "Hotel deleted sucessfully !!!");
		response.put("status", true);	
		return response;
	}
	
	private HotelResponse mapHotelToDto(Hotel hotel) {
		HotelResponse response = new HotelResponse();
		response.setId(hotel.getId());
		response.setName(hotel.getName());
		response.setAbout(hotel.getAbout());
		response.setLocation(hotel.getLocation());
		return response;
	}

	private void mapDtoToHotel(Hotel hotel, HotelRequest hotelRequest) {
		hotel.setName(hotelRequest.getName());
		hotel.setAbout(hotelRequest.getAbout());
		hotel.setLocation(hotelRequest.getLocation());
	}

}
