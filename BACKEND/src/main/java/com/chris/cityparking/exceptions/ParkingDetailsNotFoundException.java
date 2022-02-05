package com.chris.cityparking.exceptions;

public class ParkingDetailsNotFoundException extends RuntimeException{

    public ParkingDetailsNotFoundException(String numberPlate){
        super("could not find parking details with number plate : " + numberPlate);
    }
}
