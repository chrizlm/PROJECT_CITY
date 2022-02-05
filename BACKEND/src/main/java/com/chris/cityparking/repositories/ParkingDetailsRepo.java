package com.chris.cityparking.repositories;

import com.chris.cityparking.modules.ParkingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingDetailsRepo extends JpaRepository<ParkingDetails, Long> {
    String FIND_PARKING_LOT_DETAIL = "SELECT * FROM parking_details pd WHERE pd.location =:locationName AND pd.parking_lot_name =:parkingLotName AND plad.parking_date =:date";
    //@Query(value = FIND_PARKING_LOT_DETAIL, nativeQuery = true)
    //List<ParkingDetails> getByParkingLotNameAndLocationAndParkingDate(@Param("locationName") String locationName, @Param("parkingLotName") String parkingLotName, @Param("date") Date date);
    @Query(value = FIND_PARKING_LOT_DETAIL, nativeQuery = true)
    ParkingDetails getByParkingLotNameAndLocationAndParkingDateOne(@Param("locationName") String locationName, @Param("parkingLotName") String parkingLotName, @Param("date") Date date);

    @Query("SELECT pd FROM ParkingDetails pd WHERE pd.location =:#{#parkingDetails.location} AND pd.parkingLotName =:#{#parkingDetails.parkingLotName} AND pd.parkingDate =:#{#parkingDetails.parkingDate}")
    List<ParkingDetails> getByParkingLotNameAndLocationAndParkingDate(@Param("parkingDetails")ParkingDetails parkingDetails);

    Optional<ParkingDetails> findByNumberPlate(String numberPlate);
    List<ParkingDetails> getByNumberPlate(String numberPlate);
    void deleteByNumberPlate(String numberPlate);
    List<ParkingDetails> getByParkingLotName(String ParkingLotName);
    List<ParkingDetails> getByParkingDate(Date date);
}

/**
 * repo for bookings
 * with custom queries
 */