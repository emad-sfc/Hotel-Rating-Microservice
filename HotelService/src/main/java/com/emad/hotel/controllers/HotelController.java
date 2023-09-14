package com.emad.hotel.controllers;

import com.emad.hotel.entities.Hotel;
import com.emad.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    //create
    //only admins will be able to access this create method
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){

        /*Hotel hotel1 = hotelService.create(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);*/

        //same as below code

        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));

    }

    //get single

    @PreAuthorize("hasAuthority('scope_internal')")
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getSingleHotel(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(id));
    }


    //get all
    @PreAuthorize("hasAuthority('scope_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAll());
    }


}
