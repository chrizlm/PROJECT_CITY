package com.chris.cityparking.exceptions;

public class ParkingLotNotFoundException extends RuntimeException{

    public ParkingLotNotFoundException(String parkingRegNo){
        super("could not find parking lot with registration number : " + parkingRegNo);
    }
}
