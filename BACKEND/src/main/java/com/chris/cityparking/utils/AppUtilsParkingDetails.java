package com.chris.cityparking.utils;

import com.chris.cityparking.modules.ParkingDetails;
import com.chris.cityparking.repositories.ParkingDetailsRepo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
@EnableAsync
public class AppUtilsParkingDetails {
    @Autowired
    ParkingDetailsRepo parkingDetailsRepo;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date todaysDate = Calendar.getInstance().getTime();

    //@DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    Date todaysTime = Calendar.getInstance().getTime();


    @Async
    @Scheduled(cron = "0 0 * * * *")
    public void removeRecord(){
        log.info("trying to remove expired parking ... ");

        List<ParkingDetails> recordList = parkingDetailsRepo.getByParkingDate(todaysDate);

        for(ParkingDetails parkingDetails : recordList){

            LocalTime localExpireTime = LocalTime.ofInstant(parkingDetails.getExpiryParkTime().toInstant(), ZoneId.of("UTC"));
            LocalTime todaysLocalTime = LocalTime.ofInstant(todaysTime.toInstant(), ZoneId.of("UTC"));

            if(todaysLocalTime.isAfter(localExpireTime)){
                parkingDetailsRepo.delete(parkingDetails);
            }
        }
    }


}

/**
 * automatically checks for expired parking details after an hour
 */