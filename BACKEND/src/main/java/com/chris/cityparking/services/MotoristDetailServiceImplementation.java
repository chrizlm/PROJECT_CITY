package com.chris.cityparking.services;

import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.exceptions.MotoristNotFoundException;
import com.chris.cityparking.modules.MotoristDetails;
import com.chris.cityparking.repositories.MotoristDetailsRepo;
import com.chris.cityparking.utils.AppUtilsMotorist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class MotoristDetailServiceImplementation implements MotoristDetailService{
    private final PasswordEncoder passwordEncoder;

    @Autowired
    MotoristDetailsRepo motoristDetailsRepo;


    @Autowired
    AppUtilsMotorist appUtilsMotorist;

    @Autowired
    AppUserService appUserService;

    @Autowired
    EmailServices emailServices;


    /*
    getting details
    saving details
    update details
    deleting details
     */

    //create motorist details record
    @Override
    public void createMotoristDetail(MotoristDetails motoristDetails){
        log.info("saving motorist details for {}", motoristDetails.getFirstName());
        motoristDetails.setPassword(passwordEncoder.encode(motoristDetails.getPassword()));
        motoristDetailsRepo.save(motoristDetails);

        //send details to helper class to save instance of app user
        appUtilsMotorist.dealWithAppUser(motoristDetails);

        //send email to mail class to send notification to motorist
        emailServices.sendRegistrationMailMotorist(motoristDetails.getEmail());
    }

    //getting motorist detail using id
    @Override
    public List<MotoristDetails> getMotorist(Long motoristID){
        log.info("getting motorist details of motorist {}" , motoristID);
        return motoristDetailsRepo.getByMotoristID(motoristID);
    }

    //getting a list of all motorists
    @Override
    public List<MotoristDetails> getAllMotorists(){
        log.info("getting a list of all motorist details records");
        return motoristDetailsRepo.findAll();
    }

    //updating motorist details
    @Override
    public void updateMotorist(MotoristDetails motoristDetails){

        motoristDetailsRepo.findById(motoristDetails.getMotoristID()).ifPresentOrElse(motorist ->{
            motorist.setFirstName(motoristDetails.getFirstName());
            motorist.setLastName(motoristDetails.getLastName());
            motorist.setEmail(motoristDetails.getEmail());
            motorist.setGender(motoristDetails.getGender());
            motorist.setPassword(motoristDetails.getPassword());
            log.info("updating motorist details for {}", motoristDetails.getFirstName());
            motoristDetailsRepo.save(motorist);
        }, () ->{
            log.info("motorist details for {} not found", motoristDetails.getFirstName());
            throw new MotoristNotFoundException(motoristDetails.getMotoristID());
        });
    }

    //changing motorist password
    @Override
    public void updateMotoristDetailPassword(EmailPasswordForm emailPasswordForm){
        log.info("changing motorist password for {}", emailPasswordForm.getEmail());
        MotoristDetails newMotoristInfo = getMotoristDetails(emailPasswordForm.getEmail());
        //newMotoristInfo.setPassword(passwordEncoder.encode(emailPasswordForm.getNewpassword()));
        newMotoristInfo.setPassword(emailPasswordForm.getNewpassword());
        motoristDetailsRepo.save(newMotoristInfo);
        appUserService.changeUserPassword(emailPasswordForm);
    }

    //delete motorist record using id
    @Override
    public void deleteMotorist(Long motoristID){
        log.info("deleting motorist record");
        motoristDetailsRepo.deleteById(motoristID);
    }

    //delete motorist record using id
    @Override
    public void deleteMotoristEmail(String email){
        log.info("deleting motorist record for {}", email);
        motoristDetailsRepo.deleteByEmail(email);

        //pass email to helper class to delete instance of the user
        appUtilsMotorist.dealWithAppUserDelete(email);
    }

    //get motorist details using email
    @Override
    public MotoristDetails getMotoristDetails(String email) {
        log.info("getting motorist details for {}", email);
        return motoristDetailsRepo.getByEmail(email);
    }
}

/**
 * basic services for motorist details
 * save
 * get
 * update
 * delete
 */