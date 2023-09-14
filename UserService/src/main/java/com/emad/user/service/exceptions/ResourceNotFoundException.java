package com.emad.user.service.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){

        super("Resource Not Found on server");
    }

    public ResourceNotFoundException(String message){

        super(message);
    }

}
