package com.chris.cityparking.utils;

import com.chris.cityparking.modules.ParkingLot;
import com.chris.cityparking.modules.ParkingLotAndDates;
import com.chris.cityparking.repositories.ParkingLotAndDatesRepo;
import com.chris.cityparking.services.ParkingLotAndDateService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@Service
public class AppUtilsParkingLotDates {
    @Autowired
    ParkingLotAndDateService parkingLotAndDateService;
    @Autowired
    ParkingLotAndDatesRepo parkingLotAndDatesRepo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date todaysDate = Calendar.getInstance().getTime();



//creates parking lots for spacified days
    public void dealWithParkingLotAndDate(ParkingLot parkingLot) {

        //duration of 6 days
        int start = 0, end = 5;
        log.info("creating parking lots for {} days", end);

        while(start <= end) {

            ParkingLotAndDates parkingLotAndDates = new ParkingLotAndDates();
            parkingLotAndDates.setParkingLotName(parkingLot.getParkingLotName());
            parkingLotAndDates.setRegNo(parkingLot.getParkingRegNo());
            //parkingLotAndDates.setParkingRegNo(parkingLot.getParkingRegNo());
            parkingLotAndDates.setTotalCapacity(parkingLot.getTotalParkingSpaces());
            //parkingLotAndDates.setAvailableSpace(parkingLot.getTotalParkingSpaces());
            parkingLotAndDates.setParkingLotLocation(parkingLot.getParkingLotLocation());


            Calendar calendar = Calendar.getInstance();
            calendar.setTime(todaysDate);
            calendar.add(Calendar.DATE, start);

            parkingLotAndDates.setDate(calendar.getTime());

            parkingLotAndDateService.saveParkLotAndDate(parkingLotAndDates);

            start++;

        }

    }

    public void dealWithParkingLotAndDateDelete(String regNumber){
        parkingLotAndDatesRepo.deleteByRegNo(regNumber);
    }

    public void dealWithParkingLotAndDateDeleteAll(){
        parkingLotAndDatesRepo.deleteAll();
    }

}

/**
 * creates parking lots for days specified by the number in the loop argument
 */