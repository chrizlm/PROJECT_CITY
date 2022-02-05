package com.chris.cityparking.controllers;

import com.chris.cityparking.modules.LocationParkAreas;
import com.chris.cityparking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/apiv1/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;


    //get occupied spaces of a parking lot
    @GetMapping("/occupiedSpaces/{parkingLotName}")
    public int getOccupiedSpaces(@PathVariable String parkingLotName){
        return bookingService.getOccupiedSpaces(parkingLotName);
    }


    //get areas where there are parking lots
    @GetMapping("/locdata")
    public ResponseEntity<Set<String>> getLocationData(){
        return new ResponseEntity<>(bookingService.getAreas(), HttpStatus.OK);
    }

    //get a list of locations that have parkings
    @GetMapping("/locdataList")
    public ResponseEntity<List<LocationParkAreas>> getLocationListData(){
        return new ResponseEntity<>(bookingService.getAllParkingsLocationAndAreas(), HttpStatus.OK);
    }


    //getting List of parking lots in a locaton
    @GetMapping(value = "/parkingdata/{locationName}", produces = "application/json")
    public ResponseEntity<List<String>> getParkingData(@PathVariable String locationName){
        return new ResponseEntity<>(bookingService.getParkings(locationName), HttpStatus.OK);
    }

}

/**
 * this controller to handle requests that are vital to the booking process like
 - getting occupied spaces of the interested parking lot
 - getting list of parking lots according to location
 - getting area and locations of the parking lots
 */

