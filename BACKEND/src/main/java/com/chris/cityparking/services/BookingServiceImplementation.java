package com.chris.cityparking.services;


import com.chris.cityparking.modules.LocationParkAreas;
import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.modules.ParkingLotAndDates;
import com.chris.cityparking.repositories.LocationParkAreasRepo;
import com.chris.cityparking.repositories.ParkingDetailsRepo;
import com.chris.cityparking.repositories.ParkingLotAndDatesRepo;
import com.chris.cityparking.repositories.ParkingLotRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class BookingServiceImplementation implements BookingService{
    @Autowired
    ParkingLotRepo parkingLotRepo;

    @Autowired
    ParkingDetailsRepo parkingDetailsRepo;

    @Autowired
    LocationParkAreasRepo locationParkAreasRepo;

    @Autowired
    ParkingLotAndDatesRepo parkingLotAndDatesRepo;

    @Autowired
    ParkingLotAndDateService parkingLotAndDateService;


    /*
    //get occupied spaces
    use the parking lot name in parking lot repo to find a parking lot
    then use the count method to determine the number of bookings made
     */
    @Override
    public int getOccupiedSpaces(String parkingLotName){
        log.info("getting the number of occupied spaces of {} parking lot", parkingLotName);
        return parkingDetailsRepo.getByParkingLotName(parkingLotName).size();
    }

    /*
    get booking spaces
    -get list of booking details and find size of list
     */
    @Override
    public int getBookedSpaces(ParkingDetails parkingDetails){
        log.info("getting booked spaces of parking lot");
        List<ParkingDetails> booked = parkingDetailsRepo.getByParkingLotNameAndLocationAndParkingDate(parkingDetails);
        return booked.size();
    }


    /*
    //get available free spaces
    find a parking lot and date using parking lot name
    get the total capacity
    then get the occupied spaces
    save the difference of the two (occupied from total capacity) on the parking lot and date record
    */
    @Override
    public void getAvailableFreeSpacePerDate(String parkingLotName){
        log.info("getting available spaces of {} parking lot", parkingLotName);
        ParkingLotAndDates parkingLotAndDates = parkingLotAndDatesRepo.getByParkingLotName(parkingLotName);
        int totalCapacity = parkingLotAndDates.getTotalCapacity();
        int occupiedCapacity = getOccupiedSpaces(parkingLotName);
        int freeSpaces = (totalCapacity - occupiedCapacity);
        parkingLotAndDates.setAvailableSpace(freeSpaces);
    }

    /*
    //updating free spaces on parking lot and dates
    -find a parking lot and date using parking lot details
    get the total capacity
    then get the occupied spaces
    save the difference of the two (occupied from total capacity) on the parking lot and date record
     */
    @Override
    public void updateFreeSpacesWithDates(ParkingDetails parkingDetails){
        log.info("getting available free spaces of parking lot with date");
        ParkingLotAndDates parkingLotAndDates = parkingLotAndDateService.getAParking(parkingDetails);
        int totalCapacity = parkingLotAndDates.getTotalCapacity();
        int occupiedCapacity = getBookedSpaces(parkingDetails);
        if(occupiedCapacity < totalCapacity) {
            int freeSpaces = ((totalCapacity - 1) - occupiedCapacity);
            parkingLotAndDates.setAvailableSpace(freeSpaces);
            parkingLotAndDatesRepo.save(parkingLotAndDates);
        }
    }



    /*
    //retrieve a list of bookings
    get specific parking lot
    retrieve all the bookings
     */
    @Override
    public List<ParkingDetails> getAllParkingDetailsInAParkingLot(String parkingLotName){
        log.info("in booking getting all parking details of {}", parkingLotName);
        return parkingDetailsRepo.getByParkingLotName(parkingLotName);
    }



    //get areas of parking lots
    @Override
    public Set<String> getAreas(){
        log.info("in booking getting all parking lot location");
        return locationParkAreasRepo.findLocation();
    }


    /*
   //get parking lots in a location
   use repo to retrieve the parking lots
    */
    @Override
    public List<String> getParkings(String locationName){
        //log.info("getting parking lots of {} area", locationName);
        return locationParkAreasRepo.findParkingLots(locationName);
    }

    //get all location of parking lots
    @Override
    public List<LocationParkAreas> getAllParkingsLocationAndAreas(){
        log.info("getting all locations of parking");
        return locationParkAreasRepo.findAll();
    }


}

/**
 * this service is to assist in the booking process
 * creation of parking detail records(the actual booking)
 */