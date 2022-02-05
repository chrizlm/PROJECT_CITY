package com.chris.cityparking.services;

import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.modules.ParkingLotAndDates;
import com.chris.cityparking.repositories.ParkingDetailsRepo;
import com.chris.cityparking.repositories.ParkingLotAndDatesRepo;
import com.chris.cityparking.repositories.ParkingLotRepo;
import com.chris.cityparking.utils.PaymentUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class ParkingDetailServiceImplementation implements ParkingDetailService{
    @Autowired
    ParkingDetailsRepo parkingDetailsRepo;
    @Autowired
    ParkingLotRepo parkingLotRepo;
    @Autowired
    BookingService bookingService;
    @Autowired
    ParkingLotAndDateService parkingLotAndDateService;
    @Autowired
    ParkingLotAndDatesRepo parkingLotAndDatesRepo;
    @Autowired
    EmailServices emailServices;
    @Autowired
    PaymentUtil paymentUtil;


    /*
    getting details
    saving details
    update details
    deleting details
     */

    //getting available spaces for booking
    @Override
    public Integer getAvailableSpaceForBooking(ParkingDetails parkingDetails){
        log.info("getting available spaces in {} parking lot", parkingDetails.getParkingLotName());
        return parkingLotAndDateService.getAvailableSpaces(parkingDetails);
    }

    //getting the cost of booking
    @Override
    public double getAmountToBePaid(ParkingDetails parkingDetails){
        log.info("getting the total cost of booking");
        return paymentUtil.calcPayment(parkingDetails.getParkDuration(),parkingDetails.getVehicleType());
    }

    //adding the expiry time to the parking detail record
    public ParkingDetails updateexpiryTime(ParkingDetails parkingDetails){
        ParkingDetails parkingDetails1 = new ParkingDetails();
        parkingDetails1.setNumberPlate(parkingDetails.getNumberPlate());
        parkingDetails1.setVehicleType(parkingDetails.getVehicleType());
        parkingDetails1.setLocation(parkingDetails.getLocation());
        parkingDetails1.setParkingLotName(parkingDetails.getParkingLotName());
        parkingDetails1.setParkingDate(parkingDetails.getParkingDate());
        parkingDetails1.setParkTime(parkingDetails.getParkTime());
        parkingDetails1.setParkDuration(parkingDetails.getParkDuration());


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parkingDetails.getParkTime());
        calendar.add(Calendar.HOUR, parkingDetails.getParkDuration());
        ;

        log.info("setting the expiry time for the booking");
        parkingDetails1.setExpiryParkTime(calendar.getTime());

        return parkingDetails1;

    }

    //saving the booking record
    @Override
    public String saveParkingDetail(ParkingDetails parkingDetails, String email){
        log.info("trying to save the booking ...");
        int availableSpaces = parkingLotAndDateService.getAvailableSpaces(parkingDetails);
        int totalSpaces = parkingLotAndDateService.getTotalSpaces(parkingDetails);
        log.info("checking available space if its enough");
        if(availableSpaces >= 0 || availableSpaces <= totalSpaces ){
            ParkingDetails parkingDetails1 = updateexpiryTime(parkingDetails);
            //List<ParkingLotAndDates> parkingLotAndDates = parkingLotAndDateService.getListParkings(parkingDetails1);
            ParkingLotAndDates parkingLotAndDates = parkingLotAndDateService.getAParking(parkingDetails1);
            bookingService.updateFreeSpacesWithDates(parkingDetails1);
            double amountPaid = paymentUtil.calcPayment(parkingDetails.getParkDuration(),parkingDetails.getVehicleType());
            parkingDetails1.setAmountPaid(amountPaid);
            parkingDetails1.setParkingLotAndDates(parkingLotAndDates);
            parkingDetailsRepo.save(parkingDetails1);
            emailServices.sendBookingDetailsMailMotorist(parkingDetails1, email);
            log.info("booking saved successfully");
            return "Successful booked";
        }else {
            log.info("booking failed due to lack of space");
            return "failed booking";
        }

    }

    //get parking details according to number plate
    @Override
    public List<ParkingDetails> getParkingDetail(String numberPlate){
        log.info("getting parking details for plate number {}", numberPlate);
        return parkingDetailsRepo.getByNumberPlate(numberPlate);
    }

    //get a list of all parking details
    @Override
    public List<ParkingDetails> getAllParkingDetails(){
        log.info("getting a list of all parking details");
        return parkingDetailsRepo.findAll();
    }

    //delete parking details according to number plate
    @Override
    public void deleteParkingDetails(String numberPlate){
        log.info("deleting parking details for plate number {}", numberPlate);
        parkingDetailsRepo.deleteByNumberPlate(numberPlate);
    }

    //delete all parking details
    @Override
    public void deleteAllParkingDetails(){
        log.info("deleting all parking details");
        parkingDetailsRepo.deleteAll();
    }
}

/**
 * has basic services for parking details
 * save
 * get
 * update
 * delete
 */