package com.chris.cityparking.repositories;

import com.chris.cityparking.modules.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingLotRepo extends JpaRepository<ParkingLot, Long> {
    ParkingLot getByParkingRegNo(String parkingRegNo);

    Optional<ParkingLot> findByParkingRegNo(String parkingRegNo);

    void deleteByParkingRegNo(String parkingRegNo);

    ArrayList<ParkingLot> findAllByParkingLotLocation(String location);

    Optional<ParkingLot> findParkingLotByParkingLotName(String parkingName);

    //for park detail
    ParkingLot getByParkingLotName(String parkingLotName);
}

/**
 * repo for parking lot
 */