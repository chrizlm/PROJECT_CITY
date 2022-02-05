package com.chris.cityparking.controllers;

import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.modules.AdminDetails;
import com.chris.cityparking.services.AdminDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/apiv1/admin")
public class AdminDetailsController {
    @Autowired
    AdminDetailService adminDetailService;


    //saving admin credatials
    @PostMapping("/save")
    public ResponseEntity<AdminDetails> createAdmin(@RequestBody AdminDetails adminDetails){
        adminDetailService.createAdminDetail(adminDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //getting admin details through by email
    @GetMapping("/get/{email}")
    public ResponseEntity<AdminDetails> getAdminInfo(@PathVariable String email){
        return new ResponseEntity<>(adminDetailService.getAdminInfo(email),HttpStatus.OK);
    }


    //updating admin details
    @PutMapping("/update")
    public ResponseEntity<?> updateAdmin(@RequestBody AdminDetails adminDetails){
        adminDetailService.updateAdminDetail(adminDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //changing of admin details
    @PutMapping("/updatePassword")
    public ResponseEntity<?> updateAdminPassword(@RequestBody EmailPasswordForm emailPasswordForm){
        adminDetailService.updateAdminDetailPassword(emailPasswordForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //deleting admin details by id
    @DeleteMapping("/{adminID}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminID){
        adminDetailService.deleteAdminDetail(adminID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //deleting admin details by email
    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteAdminByEmail(@PathVariable String email){
        adminDetailService.deleteAdminDetailEmail(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

/**
Setting up a controller for admin
 -some end points need to be accessed in front end eg changing of password
 -some will need for putting admin details at backend
 -find a way to create default admin details at start up
 */