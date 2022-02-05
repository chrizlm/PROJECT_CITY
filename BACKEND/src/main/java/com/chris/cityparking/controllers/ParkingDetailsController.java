package com.chris.cityparking.controllers;


import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.services.ParkingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@Controller
@RequestMapping("/apiv1/parkingdetails")
public class ParkingDetailsController {
    @Autowired
    ParkingDetailService parkingDetailService;

    //request to check for enough space
    @PostMapping("/checkSpace")
    public ResponseEntity<Integer> getSpacesForBooking(@RequestBody ParkingDetails parkingDetails){
        return new ResponseEntity<>(parkingDetailService.getAvailableSpaceForBooking(parkingDetails), HttpStatus.OK);
    }

    //getting the cost of the booking
    @PostMapping("/amount")
    public ResponseEntity<Double> getAmountForBooking(@RequestBody ParkingDetails parkingDetails){
        return new ResponseEntity<>(parkingDetailService.getAmountToBePaid(parkingDetails), HttpStatus.OK);
    }

    //saving the booking record
    @PostMapping("/save/{email}")
    public ResponseEntity<String> createParkingDetail(@RequestBody ParkingDetails parkingDetails, @PathVariable String email){
        return new ResponseEntity<>(parkingDetailService.saveParkingDetail(parkingDetails, email), HttpStatus.CREATED);
    }

    //getting bookings using number plate
    @GetMapping("/get/{numberPlate}")
    public ResponseEntity<List<ParkingDetails>> getParkingDetails(@PathVariable String numberPlate){
        return new ResponseEntity<>(parkingDetailService.getParkingDetail(numberPlate), HttpStatus.OK);
    }

    //getting the complete list of bookings
    @GetMapping("/all")
    public ResponseEntity<List<ParkingDetails>> getAllParkingDetails(){
        return new ResponseEntity<>(parkingDetailService.getAllParkingDetails(), HttpStatus.OK);
    }

    //removing a booking record
    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAllParkingDetails(){
        parkingDetailService.deleteAllParkingDetails();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

/**
 * this controller is to handle request of the actual booking of parking such as
 * checking if there are enough spaces
 * getting a booking record using number plate
 */