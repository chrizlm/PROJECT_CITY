package com.chris.cityparking.exceptions;


public class AttendantNotFoundException extends RuntimeException {
    public AttendantNotFoundException(Long attendantID){
        super("could not find motorist with id " + attendantID);
    }
}
