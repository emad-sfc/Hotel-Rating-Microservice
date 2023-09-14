package com.emad.hotel.services.impl;

import com.emad.hotel.entities.Hotel;
import com.emad.hotel.exceptions.ResourceNotFoundException;
import com.emad.hotel.repositories.HotelRespository;
import com.emad.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRespository hotelRespository;
    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRespository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRespository.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRespository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Hotel with given id not found"));
    }
}
