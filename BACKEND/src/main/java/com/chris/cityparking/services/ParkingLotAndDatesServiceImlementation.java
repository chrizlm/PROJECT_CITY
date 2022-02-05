package com.chris.cityparking.services;

import com.chris.cityparking.controllers.Forms.LocationAndDateForm;
import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.modules.ParkingLotAndDates;
import com.chris.cityparking.repositories.ParkingLotAndDatesRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class ParkingLotAndDatesServiceImlementation implements ParkingLotAndDateService{
    @Autowired
    ParkingLotAndDatesRepo parkingLotAndDatesRepo;


    //saving parking lots with dates
    @Override
    public ParkingLotAndDates saveParkLotAndDate(ParkingLotAndDates parkingLotAndDates) {
        log.info("saving parking lot {} with date to the database", parkingLotAndDates.getParkingLotName());
        return parkingLotAndDatesRepo.save(parkingLotAndDates);
    }


    //get list of parking lot with dates according to parking lot name
    @Override
    public List<ParkingLotAndDates> getParkingLotAndDates(String parkingLotName) {
        log.info("fetching parking lot and date {} ", parkingLotName);
        return (List<ParkingLotAndDates>) parkingLotAndDatesRepo.getByParkingLotName(parkingLotName);
    }

    //get list of parking lot with dates according to date
    @Override
    public List<ParkingLotAndDates> getListByDates(Date date) {
        log.info("fetch list of parking with dates by date {}", date);
        return parkingLotAndDatesRepo.getByDate(date);
    }

    //get list of parking lot with dates according to location
    @Override
    public List<ParkingLotAndDates> getListByLocation(String location) {
        log.info("fetch list of parking with dates by location {}", location);
        return parkingLotAndDatesRepo.getByParkingLotLocation(location);
    }

    //get list of parking lot with dates according to parking lot name, location and date
    @Override
    public List<ParkingLotAndDates> getParkings(ParkingDetails parkingDetails){
        log.info("fetch list of parking with dates by location: {} name:{} and date {}",
                parkingDetails.getLocation(),
                parkingDetails.getParkingLotName(),
                parkingDetails.getParkingDate());
        return parkingLotAndDatesRepo.getByParkingLotLocationAndParkingLotNameAndDate(
                parkingDetails.getLocation(),
                parkingDetails.getParkingLotName(),
                parkingDetails.getParkingDate()
        );
    }

    //get list of parking lot with dates according to parking lot name, location and date
    //second method
    @Override
    public List<ParkingLotAndDates> getListParkings(ParkingDetails parkingDetails){
        log.info("fetch list of parking with dates by location: {} name:{} and date {}",
                parkingDetails.getLocation(),
                parkingDetails.getParkingLotName(),
                parkingDetails.getParkingDate());
        return parkingLotAndDatesRepo.getByParkingLotLocationAndParkingLotNameAndDateTwoList(parkingDetails);
    }

    //getting a parking lot and date
    //method one
    @Override
    public ParkingLotAndDates getAParking(ParkingDetails parkingDetails){
        log.info("getting a parking lot with date");
        return parkingLotAndDatesRepo.getByParkingLotLocationAndParkingLotNameAndDateTwo(parkingDetails);

    }

    //getting a parking lot and date
    //method two
    @Override
    public ParkingLotAndDates getAParkingTwo(ParkingDetails parkingDetails){
        log.info("getting a parking lot with date");
        return parkingLotAndDatesRepo.getByParkingLotLocationAndParkingLotNameAndDateTwo(parkingDetails);
    }

    //get list of parking lot with dates according to location and date
    @Override
    public List<ParkingLotAndDates> getListByLocationAndDate(LocationAndDateForm locationAndDateForm) {
        log.info("fetch list of parking with dates by location: {} and date {}",
                locationAndDateForm.getLocation(),
                locationAndDateForm.getParkingDate());
        return  parkingLotAndDatesRepo.getByParkingLotLocationAndParkingLotNameAndDateThree(locationAndDateForm);
    }

    //get available spaces
    @Override
    public Integer getAvailableSpaces(ParkingDetails parkingDetails) {
        log.info("getting the available spaces of {} parking lot", parkingDetails.getParkingLotName());
        ParkingLotAndDates parkingLotAndDates = getAParking(parkingDetails);
        return parkingLotAndDates.getAvailableSpace();
    }

    //get total space of parking lot
    @Override
    public int getTotalSpaces(ParkingDetails parkingDetails) {
        log.info("getting the total spaces of {} parking lot", parkingDetails.getParkingLotName());
        ParkingLotAndDates parkingLotAndDates = getAParking(parkingDetails);
        return parkingLotAndDates.getTotalCapacity();
    }


    //get all parking lot and dates
    @Override
    public List<ParkingLotAndDates> getAllParkingLotAndDates() {
        log.info("fetching all parking lot and date");
        return parkingLotAndDatesRepo.findAll();
    }
}

/**
 * has basic services for parking lots and dates
 * save
 * get
 * update
 * delete
 */