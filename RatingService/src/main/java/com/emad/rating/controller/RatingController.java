package com.emad.rating.controller;

import com.emad.rating.entities.Rating;
import com.emad.rating.services.RatingService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    //create rating
    //only admin can have access
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }

    //get All
    @GetMapping
    public ResponseEntity<List<Rating>> getRatings() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    // get by user id
    @PreAuthorize("hasAuthority('scope_internal') || hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingsbyUserId(userId));
    }

    // get by hotel id
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getByhotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.ratingByHotelId(hotelId));
    }

}
