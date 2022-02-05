package com.chris.cityparking.repositories;

import com.chris.cityparking.modules.LocationParkAreas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface LocationParkAreasRepo extends JpaRepository<LocationParkAreas,Long> {

    String FIND_LOCATION = "SELECT location FROM location_park_areas";
    String FIND_PARKING_LOTS = "SELECT parking_lot_name FROM location_park_areas lpk where lpk.location =:locationName";

    @Query(value = FIND_LOCATION, nativeQuery = true)
    Set<String> findLocation();

    @Query(value = FIND_PARKING_LOTS, nativeQuery = true)
    List<String> findParkingLots(@Param("locationName") String locationName);
}

/**
 * repo for location and parking
 */