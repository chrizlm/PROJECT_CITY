package com.chris.cityparking.exceptions;



public class MotoristNotFoundException extends RuntimeException{
    public MotoristNotFoundException(Long motoristID){
        super("could not find motorist with id " + motoristID);
    }
}
