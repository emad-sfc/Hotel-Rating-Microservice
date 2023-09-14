package com.emad.user.service.services.impl;

import com.emad.user.service.entities.Hotel;
import com.emad.user.service.entities.Rating;
import com.emad.user.service.entities.User;
import com.emad.user.service.exceptions.ResourceNotFoundException;
import com.emad.user.service.external.services.HotelService;
import com.emad.user.service.repositories.UserRepository;
import com.emad.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        //implement RATING SERVICE CALL: Using REST Template

        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with Given Id not found on server !"+userId));

        //http://localhost:8083/ratings/users/ab184b7c-8144-4db5-9a19-89b141c6ee2d

        //passing harcoded value
        //ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/ab184b7c-8144-4db5-9a19-89b141c6ee2d", ArrayList.class);

        //passing dynamic id
//        ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);
//
//
//
//
//        logger.info("{}",ratingOfUser);
//        user.setRatings(ratingOfUser);
//
//        return user;


        //calling rating service, which is calling hotel service from user service
        //user->rating->hotel




        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-MICROSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{} ",ratingOfUser);

        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:8081/hotels/900d621e-c8dc-4a65-ba35-e96a6620d0ad

            //Here we were calling through RestTemplate. Not required now,as we are using Feign client to call hotel service.
            // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-MICROSERVICE/hotels/"+rating.getHotelId(), Hotel.class);


            //Using Feign client for Hotel Service
            //calling Rating service using rest template, and calling hotel service using FEIGN client
            Hotel hotel = hotelService.getHotel(rating.getHotelId());

            //set the hotel to rating
            rating.setHotel(hotel);

            //return rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;


    }

    @Override
    public void deleteUser(String userID) {
        userRepository.deleteById(userID);
        System.out.println("User Id "+userID+" deleted from server !");
    }



}
