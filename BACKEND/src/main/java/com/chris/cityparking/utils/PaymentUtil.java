package com.chris.cityparking.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentUtil {
    //calculating payments
    public double calcPayment(int duration, String vehicleType){
        log.info("checking the vehicle type and calculationg payments");
        //small cars (rate) * duration
        double results = 0;

        switch (vehicleType) {
            case "Motorcycle":
                results = duration * 100;
                break;
            case "Tricycle":
                results = duration * 200;
                break;
            case "Car":
                results = duration * 500;
                break;
            case "Lorry":
                results = duration * 800;
                break;
            default:
                break;
        }
        return results;
    }
}

/**
 * calculating payments according to vehicle type and duration
 */