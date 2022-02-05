package com.chris.cityparking.services;

import com.chris.cityparking.exceptions.ParkingLotNotFoundException;
import com.chris.cityparking.modules.LocationParkAreas;
import com.chris.cityparking.modules.ParkingLot;
import com.chris.cityparking.repositories.LocationParkAreasRepo;
import com.chris.cityparking.repositories.ParkingLotRepo;
import com.chris.cityparking.utils.AppUtilsParkingLotDates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class ParkingLotServiceImplementation implements ParkingLotService{
    @Autowired
    ParkingLotRepo parkingLotRepo;
    @Autowired
    LocationParkAreasRepo locationParkAreasRepo;
    @Autowired
    AppUtilsParkingLotDates appUtilsParkingLotDates;
     /*
    getting details
    saving details
    update details
    deleting details
     */

    //create a record of a parking lot
    @Override
    public void createParkingLot(ParkingLot parkingLot){
        log.info("saving {} parking lot record", parkingLot.getParkingLotName());
        //save the location and area of parking lot
        LocationParkAreas locationParkAreas = new LocationParkAreas();
        locationParkAreas.setLocation(parkingLot.getParkingLotLocation());
        locationParkAreas.setParkingLotName(parkingLot.getParkingLotName());
        locationParkAreasRepo.save(locationParkAreas);
        parkingLotRepo.save(parkingLot);

        //pass the details to a helper class to attach dates to the parking lot
        appUtilsParkingLotDates.dealWithParkingLotAndDate(parkingLot);
    }

    //get parking lot according to the reg number
    @Override
    public ParkingLot getParkingLot(String parkingRegNo){
        log.info("fetching parking lot with reg number: {}", parkingRegNo);
        return parkingLotRepo.getByParkingRegNo(parkingRegNo);
    }

    //get all parking lots
    @Override
    public List<ParkingLot> getAllParkingLots(){
        log.info("fetching all the parking lots");
        return parkingLotRepo.findAll();
    }

    //update parking lot
    @Override
    public void updateParkingLot(ParkingLot parkingLot){
        parkingLotRepo.findByParkingRegNo(parkingLot.getParkingRegNo()).ifPresentOrElse(parklot ->{
            parklot.setParkingLotLocation(parkingLot.getParkingLotLocation());
            parklot.setParkingLotName(parkingLot.getParkingLotName());
            parklot.setTotalParkingSpaces(parkingLot.getTotalParkingSpaces());
            log.info("updating {} parking lot", parkingLot.getParkingLotName());
            parkingLotRepo.save(parklot);
        }, () ->{
            log.info("parking lot {} cannot be foung", parkingLot.getParkingLotName());
            throw new ParkingLotNotFoundException(parkingLot.getParkingRegNo());
        });
    }

    //delete parking lot according to reg number
    @Override
    public void deleteParkingLot(String parkingRegNo){
        log.info("deleting parking lot with reg number: {}", parkingRegNo);
        parkingLotRepo.deleteByParkingRegNo(parkingRegNo);
        appUtilsParkingLotDates.dealWithParkingLotAndDateDelete(parkingRegNo);
    }

    //delete all aprking lots
    @Override
    public void deleteAll(){
        log.info("deleting all parking lots");
        parkingLotRepo.deleteAll();
        appUtilsParkingLotDates.dealWithParkingLotAndDateDeleteAll();
    }

    /**
     * other services associated with parking lot
     *
     * -findAll in a location
     * -Get specific with name and location
     * -get the number of spaces in a parking
     */

    //get list of parking lot by location
    @Override
    public List<ParkingLot> getParkingLotsInLocation(String location){
        log.info("getting a list of parking lot in {}", location);
        return parkingLotRepo.findAllByParkingLotLocation(location);
    }

    //get list of parking lots by name
    @Override
    public Optional<ParkingLot> getParkingLotByName(String parkingName){
        log.info("getting {} parking lot", parkingName);
        return parkingLotRepo.findParkingLotByParkingLotName(parkingName);
    }



}
