package com.chris.cityparking.services;

import com.chris.cityparking.controllers.Forms.LocationAndDateForm;
import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.modules.ParkingLotAndDates;

import java.util.Date;
import java.util.List;

public interface ParkingLotAndDateService {
    ParkingLotAndDates saveParkLotAndDate(ParkingLotAndDates parkingLotAndDates);
    List<ParkingLotAndDates> getParkingLotAndDates(String parkingLotName);
    List<ParkingLotAndDates> getListByDates(Date date);
    List<ParkingLotAndDates> getAllParkingLotAndDates();
    List<ParkingLotAndDates> getListByLocation(String Location);
    Integer getAvailableSpaces(ParkingDetails parkingDetails);
    ParkingLotAndDates getAParking(ParkingDetails parkingDetails);
    List<ParkingLotAndDates> getParkings(ParkingDetails parkingDetails);
    int getTotalSpaces(ParkingDetails parkingDetails);
    ParkingLotAndDates getAParkingTwo(ParkingDetails parkingDetails);
    List<ParkingLotAndDates> getListByLocationAndDate(LocationAndDateForm locationAndDateForm);
    List<ParkingLotAndDates> getListParkings(ParkingDetails parkingDetails);
}

/**
 * required services for parking lots and dates
 */