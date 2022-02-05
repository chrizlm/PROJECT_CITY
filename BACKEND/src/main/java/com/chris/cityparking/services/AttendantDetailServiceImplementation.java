package com.chris.cityparking.services;

import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.exceptions.AttendantNotFoundException;
import com.chris.cityparking.modules.AttendantDetails;
import com.chris.cityparking.repositories.AttendantDetailsRepo;
import com.chris.cityparking.utils.AppUtilsAttendant;
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
public class AttendantDetailServiceImplementation implements AttendantDetailService{
    private final PasswordEncoder passwordEncoder;
    @Autowired
    AttendantDetailsRepo attendantDetailsRepo;
    @Autowired
    AppUtilsAttendant appUtilsAttendant;
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


    //creating attendant details
    @Override
    public void createAttendantDetail(AttendantDetails attendantDetails) {
        log.info("saving attendant details for {}", attendantDetails.getFirstName());
        attendantDetails.setPassword(passwordEncoder.encode(attendantDetails.getPassword()));
        attendantDetailsRepo.save(attendantDetails);

        //send details to helper class to create app user instance
        appUtilsAttendant.dealWithAppUser(attendantDetails);

        //send details to mail class to send notification to attendant
        emailServices.sendRegistrationMailAttendant(attendantDetails);
    }

    //get attendant by id
    @Override
    public List<AttendantDetails> getAttendant(Long attendantID) {
        log.info("getting attendant details for {}", attendantID);
        return attendantDetailsRepo.getByAttendantID(attendantID);
    }

    //getting a list of all attendants
    @Override
    public List<AttendantDetails> getAllAttendants() {
        log.info("getting a list of all attendants");
        return attendantDetailsRepo.findAll();
    }

    //change attendant password
    @Override
    public void updateAttendantDetailPassword(EmailPasswordForm emailPasswordForm){
        log.info("change password for {}", emailPasswordForm.getEmail());
        AttendantDetails newAttendantInfo = getAttendantDetails(emailPasswordForm.getEmail());
        newAttendantInfo.setPassword(passwordEncoder.encode(emailPasswordForm.getNewpassword()));
        attendantDetailsRepo.save(newAttendantInfo);

        //pass details to helper class to change password app user instance
        appUserService.changeUserPassword(emailPasswordForm);
    }

    //updating attendant details record
    @Override
    public void updateAttendant(AttendantDetails attendantDetails) {

        attendantDetailsRepo.findById(attendantDetails.getAttendantID()).ifPresentOrElse(attendant ->{
            attendant.setFirstName(attendantDetails.getFirstName());
            attendant.setLastName(attendantDetails.getLastName());
            attendant.setEmail(attendantDetails.getEmail());
            attendant.setGender(attendantDetails.getGender());
            attendant.setPassword(attendantDetails.getPassword());
            attendant.setAreaAssigned(attendantDetails.getAreaAssigned());
            log.info("updating attendant details for {}", attendantDetails.getFirstName());
            attendantDetailsRepo.save(attendant);
        }, () ->{
            log.info("attendant details for {} not found", attendantDetails.getFirstName());
                throw new AttendantNotFoundException(attendantDetails.getAttendantID());

        });
    }

    //delete attendant details using id
    @Override
    public void deleteAttendant(Long attendantID) {
        log.info("deleting attendant record for {}", attendantID);
        attendantDetailsRepo.deleteById(attendantID);
    }

    //delete attendant details using email
    @Override
    public void deleteAttendantEmail(String email) {
        log.info("deleting attendant record for {}", email);
        attendantDetailsRepo.deleteByEmail(email);

        //pass email to helper class to delete instance of the user
        appUtilsAttendant.dealWithAppUserDelete(email);
    }

    //getting attendant details using email
    @Override
    public AttendantDetails getAttendantDetails(String email) {
        log.info("get attendant detail for {}", email);
        return attendantDetailsRepo.getByEmail(email);
    }

    @Override
    public List<AttendantDetails> getAttendantDetailsTwo(String email) {
        log.info("get attendant detail for {}", email);
        return attendantDetailsRepo.findByEmail(email);
    }
}

/**
 * basic attendant detail services such as
 * save
 * delete
 * get
 * update
 */