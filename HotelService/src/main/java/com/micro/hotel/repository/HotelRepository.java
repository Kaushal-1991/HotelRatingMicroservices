package com.micro.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.hotel.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String>{

}
