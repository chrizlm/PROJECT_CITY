package com.chris.cityparking.services;

import com.chris.cityparking.modules.ParkingDetails;

import java.util.List;

public interface ParkingDetailService {
    //void createParkingDetail(ParkingDetails parkingDetails);
    List<ParkingDetails> getParkingDetail(String numberPlate);
    List<ParkingDetails> getAllParkingDetails();
    Integer getAvailableSpaceForBooking(ParkingDetails parkingDetails);
    //void updateParkingDetail(ParkingDetails parkingDetails);
    void deleteParkingDetails(String numberPlate);
    void deleteAllParkingDetails();
    String saveParkingDetail(ParkingDetails parkingDetails, String email);
    double getAmountToBePaid(ParkingDetails parkingDetails);
}

/**
 * required services for parking details
 */