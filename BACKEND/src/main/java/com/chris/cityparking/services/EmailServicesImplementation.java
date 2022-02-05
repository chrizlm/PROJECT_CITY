package com.chris.cityparking.services;

import com.chris.cityparking.modules.AttendantDetails;
import com.chris.cityparking.modules.ParkingDetails;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class EmailServicesImplementation implements EmailServices{


    @Autowired
    public JavaMailSender emailSender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date todaysDate = Calendar.getInstance().getTime();

    //send email to attendant for successful registration
    @Override
    public void sendRegistrationMailAttendant(AttendantDetails attendantDetails) {
        log.info("preparing to send registration mail to attendant {}", attendantDetails.getFirstName());

        String sentMessage1 = "Welcome " + attendantDetails.getLastName() + " you have been officially registered a Parking attendant. Below are your login credentials";
        String sentMessage2 = "Email:" + attendantDetails.getEmail();
        String sentMessage3 = "Password:" + attendantDetails.getPassword();
        String sentMessage4 = "You are adviced to change the password immediately after you login successfully";


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(attendantDetails.getEmail());
        simpleMailMessage.setSubject("Registration as a Parking Attendant");
        simpleMailMessage.setText(sentMessage1 + sentMessage2 + sentMessage3 + sentMessage4);
        simpleMailMessage.setSentDate(todaysDate);
        emailSender.send(simpleMailMessage);

        log.info("Email sent successfully ");


    }

    //send email to motorist for successful registration
    @Override
    public void sendRegistrationMailMotorist(String motoristEmail) {

        log.info("preparing to send registration mail to motorist {} ", motoristEmail);

        String motoristMessage1 = "Welcome " + motoristEmail + ". You are now officially registered as a Parking motorist";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(motoristEmail);
        simpleMailMessage.setSubject("Registration as a Parking motorist");
        simpleMailMessage.setText(motoristMessage1);
        simpleMailMessage.setSentDate(todaysDate);
        emailSender.send(simpleMailMessage);

        log.info("Email sent successfully ");

    }

    //send email to motorist for successful booking
    @Override
    public void sendBookingDetailsMailMotorist(ParkingDetails parkingDetails, String motoristEmail) {
        log.info("preparing to send booking details mail to motorist {}", motoristEmail);


        LocalDate localParkDate = LocalDate.ofInstant(parkingDetails.getParkingDate().toInstant(), ZoneId.systemDefault());
        LocalTime localParkTime = LocalTime.ofInstant(parkingDetails.getParkTime().toInstant(), ZoneId.of("UTC"));
        LocalTime localExpireTime = LocalTime.ofInstant(parkingDetails.getExpiryParkTime().toInstant(), ZoneId.of("UTC"));



        String motoristBookingMessage1 = "You have successfully booked a parking at " + parkingDetails.getParkingLotName() + " from " +
                localParkTime + "   on    " + localParkDate + "   for   " + parkingDetails.getParkDuration() + "hours  \n";
        String motoristBookingMessage2 = "The set booking will expire at " + localExpireTime + "   on   " + localParkDate
                + " \nPlease note that if you do not remove your vehicle at the said time of expiry action will be taken against you";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(motoristEmail);
        simpleMailMessage.setSubject("Booking details");
        simpleMailMessage.setText(motoristBookingMessage1 + motoristBookingMessage2);
        simpleMailMessage.setSentDate(todaysDate);
        emailSender.send(simpleMailMessage);

        log.info("Email sent successfully ");

    }
}

/**
 * this class if to handle notifications of
 * registration of motorists and attendants
 * bookings done by motorists
 */