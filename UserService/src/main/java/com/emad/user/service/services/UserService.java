package com.emad.user.service.services;

import com.emad.user.service.entities.User;

import java.util.List;

public interface UserService {
    //Create
    User saveUser(User user);

    //get all User
    List<User> getAllUsers();

    //Get single user by User Id
    User getUser(String userId);

    //to do
    //delete user using user id
    //update user

    void deleteUser(String userID);

// to do   User updateUser(User user);
}
