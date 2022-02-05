package com.chris.cityparking.controllers;

import com.chris.cityparking.controllers.Forms.LocationAndDateForm;
import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.modules.ParkingLotAndDates;
import com.chris.cityparking.services.ParkingLotAndDateService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/apiv1/parkanddate")
public class ParkingLotAndDateController {

    @Autowired
    ParkingLotAndDateService parkingLotAndDateService;

    //saving of parking lot that has dates (look later)
    @PostMapping("/save")
    public ResponseEntity<ParkingLotAndDates> saveParkingAndDates(@RequestBody ParkingLotAndDates parkingLotAndDates){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/apiv1/parkanddate/save").toUriString());
        return ResponseEntity.created(uri).body(parkingLotAndDateService.saveParkLotAndDate(parkingLotAndDates));
    }
    

    //get a list of parking lots with dates according to location
    @GetMapping("/getByLocation/{location}")
    public ResponseEntity<List<ParkingLotAndDates>> getParkingLotByLocation(@PathVariable String location){
        return new ResponseEntity<>(parkingLotAndDateService.getListByLocation(location), HttpStatus.OK);
    }

    //get a list of parking lots with dates according to date
    @GetMapping("/get/{date}")
    public ResponseEntity<List<ParkingLotAndDates>> getParkingLotByDates(@PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return new ResponseEntity<>(parkingLotAndDateService.getListByDates(date), HttpStatus.OK);
    }

    //getting parking lot with dates using parking details(way one)
    @GetMapping("/")
    public ResponseEntity<ParkingLotAndDates> getAPK(@RequestBody ParkingDetails parkingDetails){
        return new ResponseEntity<>(parkingLotAndDateService.getAParking(parkingDetails), HttpStatus.OK);
    }

    //getting parking lot with dates using parking details(way two)
    @GetMapping("/two")
    public ResponseEntity<ParkingLotAndDates> getAPKtwo(@RequestBody ParkingDetails parkingDetails){
        return new ResponseEntity<>(parkingLotAndDateService.getAParkingTwo(parkingDetails), HttpStatus.OK);
    }

    //get a list of all parking lots with dates
    @GetMapping("/all")
    public ResponseEntity<List<ParkingLotAndDates>> getAllParkingAndDates(){
        return new ResponseEntity<>(parkingLotAndDateService.getAllParkingLotAndDates(), HttpStatus.OK);
    }

    //get a list of parking lots with dates according to location and date
    @PostMapping("/get/bylocanddate")
    public ResponseEntity<List<ParkingLotAndDates>> getByLocAndDate(@RequestBody LocationAndDateForm locationAndDateForm){
        return new ResponseEntity<>(parkingLotAndDateService.getListByLocationAndDate(locationAndDateForm), HttpStatus.OK);
    }
}

@Data
class DateToParkLotForm{
    private String parkingLotName;
    private Date date;
}

@Data
class FindSpaceForm{
    private String parkingLotLocation;
    private String parkingLotName;
    private Date date;
}

/**
 * this controller is to handle all records of parking lots that have been attached to dates
 *
 */