package com.emad.rating.services;

import com.emad.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    //create
    Rating createRating(Rating rating);

    //get all ratings
    List<Rating> getAllRatings();

    //get all by user id
    List<Rating> getRatingsbyUserId(String userId);

    // get all by hotel
    List<Rating> ratingByHotelId(String hotelId);
}
