package com.chris.cityparking.controllers;

import com.chris.cityparking.modules.Blacklist;
import com.chris.cityparking.services.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/apiv1/blacklist")
public class BlackListController {
    @Autowired
    BlackListService blackListService;

    @PostMapping("/save")
    public ResponseEntity<Blacklist> createDefaulter(@RequestBody Blacklist blacklist){
        blackListService.save(blacklist);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{numberPlate}")
    public ResponseEntity<List<Blacklist>> getDefaulter(@PathVariable String numberPlate){
        return new ResponseEntity<>(blackListService.getDefaulter(numberPlate), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Blacklist>> getAllDefaulters(){
        return new ResponseEntity<>(blackListService.getall(), HttpStatus.OK);
    }

    @DeleteMapping("/{numberPlate}")
    public ResponseEntity<?> deleteDefaulter(@PathVariable String numberPlate){
        blackListService.delete(numberPlate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
