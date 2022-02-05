package com.chris.cityparking.services;

import com.chris.cityparking.modules.AttendantDetails;
import com.chris.cityparking.modules.ParkingDetails;

public interface EmailServices {
    void sendRegistrationMailAttendant(AttendantDetails attendantDetails);
    void sendRegistrationMailMotorist(String motoristEmail);
    void sendBookingDetailsMailMotorist(ParkingDetails parkingDetails, String motoristEmail);
}

/**
 * required services for email communication
 */