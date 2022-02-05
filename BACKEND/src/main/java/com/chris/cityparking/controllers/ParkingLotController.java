package com.chris.cityparking.controllers;

import com.chris.cityparking.modules.ParkingLot;
import com.chris.cityparking.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/apiv1/parkingLot")
public class ParkingLotController {
    @Autowired
    ParkingLotService parkingLotService;

    //saving a record of a parking lot
    @PostMapping("/save")
    public ResponseEntity<ParkingLot> createParkingLot(@RequestBody ParkingLot parkingLot){
        parkingLotService.createParkingLot(parkingLot);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //getting a parking lot using the reg number
    @GetMapping("/get")
    public ResponseEntity<ParkingLot> getParkingLot(@RequestParam(value = "params", required = false) String parkingRegNo){
        return new ResponseEntity<>(parkingLotService.getParkingLot(parkingRegNo), HttpStatus.OK);
    }

    //getting a list of parking lots using the location
    @GetMapping(value = "/get/{parkinglocation}", produces = "application/json")
    public ResponseEntity<List<ParkingLot>> getParkingLotInLocation(@PathVariable String parkinglocation){
        return new ResponseEntity<>(parkingLotService.getParkingLotsInLocation(parkinglocation), HttpStatus.OK);
    }

    //getting all the parking lots
    @GetMapping("/all")
    public ResponseEntity<List<ParkingLot>> getAllParkingLot(){
        return new ResponseEntity<>(parkingLotService.getAllParkingLots(), HttpStatus.OK);
    }

    //updating a parking lot
    @PutMapping("/update")
    public ResponseEntity<?> updateParkingLot(@RequestBody ParkingLot parkingLot){
        parkingLotService.updateParkingLot(parkingLot);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //deleting a parking lot using a reg number
    @DeleteMapping("/{parkingRegNo}")
    public ResponseEntity<?> deleteParkingLot(@PathVariable String parkingRegNo){
        parkingLotService.deleteParkingLot(parkingRegNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //deleting all parking lots
    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAllParkingLot(){
        parkingLotService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

/**
 * this controller is to handle a parking lot record
 * save
 * update
 * get
 * delete
 */