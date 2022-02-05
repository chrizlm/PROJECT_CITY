package com.chris.cityparking.controllers;


import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.modules.AttendantDetails;
import com.chris.cityparking.services.AttendantDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/apiv1/attendant")
public class AttendantDetailsController {
    @Autowired
    AttendantDetailService attendantDetailService;

    //creating an attendant details
    @PostMapping("/save")
    public ResponseEntity<AttendantDetails> createAttendant(@RequestBody AttendantDetails attendantDetails){
        attendantDetailService.createAttendantDetail(attendantDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //getting an attendant using id
    @GetMapping("/getDetails/{attendantID}")
    public ResponseEntity<List<AttendantDetails>> getAttendant(@PathVariable Long attendantID){
        return new ResponseEntity<>(attendantDetailService.getAttendant(attendantID), HttpStatus.OK);
    }

    //getting an attendant using email
    @GetMapping("/get/{email}")
    public ResponseEntity<AttendantDetails> getAttendantInfo(@PathVariable String email){
        return new ResponseEntity<>(attendantDetailService.getAttendantDetails(email), HttpStatus.OK);
    }

    //getting an attendant using email
    @GetMapping("/gettwo/{email}")
    public ResponseEntity<List<AttendantDetails>> getAttendantInfoTwo(@PathVariable String email){
        return new ResponseEntity<>(attendantDetailService.getAttendantDetailsTwo(email), HttpStatus.OK);
    }

    //getting all attendants
    @GetMapping("/getall")
    public ResponseEntity<List<AttendantDetails>> getAllAttendants(){
        return new ResponseEntity<>(attendantDetailService.getAllAttendants(), HttpStatus.OK);
    }

    //changing attendant password
    @PutMapping("/updatePassword")
    public ResponseEntity<?> updateAttendantPassword(@RequestBody EmailPasswordForm emailPasswordForm){
        attendantDetailService.updateAttendantDetailPassword(emailPasswordForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //updating attendant details
    @PutMapping("/update")
    public ResponseEntity<?> updateAttendant(@RequestBody AttendantDetails attendantDetails){
        attendantDetailService.updateAttendant(attendantDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //deleting an attendant
    @DeleteMapping("/{attendantID}")
    public ResponseEntity<?> deleteAttendant(@PathVariable Long attendantID){
        attendantDetailService.deleteAttendant(attendantID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //deleting an attendant by Email
    @DeleteMapping("/mail/{email}")
    public ResponseEntity<?> deleteAttendantByEmail(@PathVariable String email){
        attendantDetailService.deleteAttendantEmail(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

/**
 * handle all necessary request for the attendant details that will be required in frontend
 * request for change of password to be used in attendant account but rest used mostly in admin account
 */