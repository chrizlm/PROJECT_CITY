package com.chris.cityparking.controllers;

import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.modules.MotoristDetails;
import com.chris.cityparking.services.MotoristDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/apiv1/motorist")
public class MotoristDetailsController {
    @Autowired
    MotoristDetailService motoristDetailService;

    //request to save the motorist details
    @PostMapping("/save")
    public ResponseEntity<MotoristDetails> createMotorist(@RequestBody MotoristDetails motoristDetails){
        motoristDetailService.createMotoristDetail(motoristDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //get details of motorist according to id
    @GetMapping("/getDetails/{motoristID}")
    public ResponseEntity<List<MotoristDetails>> getMotorist(@PathVariable Long motoristID){
        return new ResponseEntity<>(motoristDetailService.getMotorist(motoristID), HttpStatus.OK);
    }

    //get motorist details according to email
    @GetMapping("/get/{email}")
    public ResponseEntity<MotoristDetails> getMotoristInfo(@PathVariable String email){
        return new ResponseEntity<>(motoristDetailService.getMotoristDetails(email), HttpStatus.OK);
    }

    //getting a list of all motorists
    @GetMapping("/getall")
    public ResponseEntity<List<MotoristDetails>> getAllMotorists(){
        return new ResponseEntity<>(motoristDetailService.getAllMotorists(), HttpStatus.OK);
    }

    //updating details of motorist
    @PutMapping("/update")
    public ResponseEntity<?> updateMotorist(@RequestBody MotoristDetails motoristDetails){
        motoristDetailService.updateMotorist(motoristDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //change password for a motorist
    @PutMapping("/updatePassword")
    public ResponseEntity<?> updateMotoristPassword(@RequestBody EmailPasswordForm emailPasswordForm){
        motoristDetailService.updateMotoristDetailPassword(emailPasswordForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //delete record of motorist
    @DeleteMapping("/{motoristID}")
    public ResponseEntity<?> deleteMotorist(@PathVariable Long motoristID){
        motoristDetailService.deleteMotorist(motoristID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


/**
 * this controller is to handle all request from the motorist frontend account
 * apart from getting list of all motorist ---> that is to be used in admin
 */