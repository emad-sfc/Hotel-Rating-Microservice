package com.emad.hotel.services;

import com.emad.hotel.entities.Hotel;

import java.util.List;


public interface HotelService {
    //create
    Hotel create(Hotel hotel);

    //get All
    List<Hotel> getAll();


    //get single
    Hotel getHotel(String id);
}
