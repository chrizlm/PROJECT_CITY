package com.chris.cityparking.exceptions;

public class AdminNotFoundException extends RuntimeException{
    public AdminNotFoundException(Long adminID){
        super("could not find admin with id " + adminID);
    }
}
