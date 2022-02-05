package com.chris.cityparking.repositories;

import com.chris.cityparking.modules.ParkingLotToDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotToDBRepo extends JpaRepository<ParkingLotToDB, Long> {
}

/**
 * repo for parking lot to DB
 */