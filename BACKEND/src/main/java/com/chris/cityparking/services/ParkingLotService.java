package com.chris.cityparking.services;

import com.chris.cityparking.modules.ParkingLot;

import java.util.List;
import java.util.Optional;

public interface ParkingLotService {
    void createParkingLot(ParkingLot parkingLot);
    ParkingLot getParkingLot(String parkingRegNo);
    List<ParkingLot> getAllParkingLots();
    void updateParkingLot(ParkingLot parkingLot);
    void deleteParkingLot(String parkingRegNo);
    void deleteAll();
    List<ParkingLot> getParkingLotsInLocation(String location);
    Optional<ParkingLot> getParkingLotByName(String parkingName);
}

/**
 * required services for parking lot
 */