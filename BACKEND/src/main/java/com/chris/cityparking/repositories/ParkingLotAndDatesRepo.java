package com.chris.cityparking.repositories;

import com.chris.cityparking.controllers.Forms.LocationAndDateForm;
import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.modules.ParkingLotAndDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ParkingLotAndDatesRepo extends JpaRepository<ParkingLotAndDates, Long> {
    String FIND_PARKING_LOTS = "SELECT * FROM parking_lot_and_dates plad WHERE plad.parking_lot_location =:locationName AND plad.parking_lot_name =:parkingLotName AND plad.date =:date";
    @Query(value = FIND_PARKING_LOTS, nativeQuery = true)
    List<ParkingLotAndDates> getByParkingLotLocationAndParkingLotNameAndDate(@Param("locationName") String locationName, @Param("parkingLotName") String parkingLotName, @Param("date") Date date);



    @Query(value = FIND_PARKING_LOTS, nativeQuery = true)
    ParkingLotAndDates getByParkingLotLocationAndParkingLotNameAndDateOne(@Param("locationName") String locationName, @Param("parkingLotName") String parkingLotName, @Param("date") Date date);

    //AND plad.date =:#{#parkingDetails.parkingDate}
    //plad.parkingLotLocation =:#{#parkingDetails.location} AND plad.parkingLotName =:#{#parkingDetails.parkingLotName}
    @Query("SELECT plad FROM ParkingLotAndDates plad WHERE plad.parkingLotLocation =:#{#parkingDetails.location} AND plad.parkingLotName =:#{#parkingDetails.parkingLotName} AND plad.date =:#{#parkingDetails.parkingDate}")
    ParkingLotAndDates getByParkingLotLocationAndParkingLotNameAndDateTwo(@Param("parkingDetails")ParkingDetails parkingDetails);


    @Query("SELECT plad FROM ParkingLotAndDates plad WHERE plad.parkingLotLocation =:#{#parkingDetails.location} AND plad.parkingLotName =:#{#parkingDetails.parkingLotName} AND plad.date =:#{#parkingDetails.parkingDate}")
    List<ParkingLotAndDates> getByParkingLotLocationAndParkingLotNameAndDateTwoList(@Param("parkingDetails")ParkingDetails parkingDetails);



    @Query("SELECT plad FROM ParkingLotAndDates plad WHERE plad.parkingLotLocation =:#{#locationAndDateForm.location} AND plad.parkingLotName =:#{#locationAndDateForm.parkingLotName} AND plad.date =:#{#locationAndDateForm.parkingDate}")
    List<ParkingLotAndDates> getByParkingLotLocationAndParkingLotNameAndDateThree(@Param("locationAndDateForm")LocationAndDateForm locationAndDateForm);

    ParkingLotAndDates getByParkingLotName(String parkingLotName);
    List<ParkingLotAndDates> getByDate(Date date);
    List<ParkingLotAndDates> getByParkingLotLocation(String location);

    void deleteByRegNo(String regNumber);



    //String FIND_LOCATION = "SELECT location FROM location_park_areas";
    //String FIND_PARKING_LOTS = "SELECT parking_lot_name FROM location_park_areas lpk where lpk.location =:locationName";

    //@Query(value = FIND_LOCATION, nativeQuery = true)
    //Set<String> findLocation();

    //@Query(value = FIND_PARKING_LOTS, nativeQuery = true)
    //List<String> findParkingLots(@Param("locationName") String locationName);
}

/**
 * repo for parking lots and dates
 * with custom queries
 */