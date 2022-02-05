package com.chris.cityparking.services;

import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.exceptions.AdminNotFoundException;
import com.chris.cityparking.modules.AdminDetails;
import com.chris.cityparking.repositories.AdminDetailsRepo;
import com.chris.cityparking.utils.AppUtilsAdmin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class AdminDetailServiceImplementation implements AdminDetailService{
    private final PasswordEncoder passwordEncoder;
   @Autowired
    AdminDetailsRepo adminDetailsRepo;
   @Autowired
   AppUtilsAdmin appUtilsAdmin;
   @Autowired
   AppUserService appUserService;

   //creating admin record
    @Override
    public void createAdminDetail(AdminDetails adminDetails) {
        log.info("creating admin details record for {}", adminDetails.getFirstName());
        adminDetails.setPassword(passwordEncoder.encode(adminDetails.getPassword()));
        adminDetailsRepo.save(adminDetails);

        //Passing the admin details to a helper class to create a user instance of admin
        appUtilsAdmin.dealWithAppUser(adminDetails);
    }

    //updating the admin record
    @Override
    public void updateAdminDetailNew(AdminDetails adminDetails){
        log.info("updating admin details record for {}", adminDetails.getFirstName());
        AdminDetails newAdminDetails = getAdminInfo(adminDetails.getEmail());
        newAdminDetails.setFirstName(adminDetails.getFirstName());
        newAdminDetails.setLastName(adminDetails.getLastName());
        newAdminDetails.setEmail(adminDetails.getEmail());
        newAdminDetails.setGender(adminDetails.getGender());
        newAdminDetails.setPassword(passwordEncoder.encode(adminDetails.getPassword()));
        adminDetailsRepo.save(newAdminDetails);
    }

    //changing the password of admin
    @Override
    public void updateAdminDetailPassword(EmailPasswordForm emailPasswordForm){
        log.info("updating admin password");
        AdminDetails newAdminInfo = getAdminInfo(emailPasswordForm.getEmail());
        newAdminInfo.setPassword(passwordEncoder.encode(emailPasswordForm.getNewpassword()));
        adminDetailsRepo.save(newAdminInfo);

        //pass the details to user to change the password for the user instance
        appUserService.changeUserPassword(emailPasswordForm);
    }

    //updating admin details
    @Override
    public void updateAdminDetail(AdminDetails adminDetails) {

        adminDetailsRepo.findById(adminDetails.getAdminID()).ifPresentOrElse(admin ->{
            admin.setFirstName(adminDetails.getFirstName());
            admin.setLastName(adminDetails.getLastName());
            admin.setEmail(adminDetails.getEmail());
            admin.setGender(adminDetails.getGender());
            admin.setPassword(adminDetails.getPassword());
            log.info("updating admin details record for {}", adminDetails.getFirstName());
            adminDetailsRepo.save(admin);
        }, () ->{
            log.info("admin details record for {} not found", adminDetails.getFirstName());
            throw new AdminNotFoundException(adminDetails.getAdminID());
        });
    }

    //deleting the admin record using id
    @Override
    public void deleteAdminDetail(Long adminID) {
        log.info("deleting admin details record for {}", adminID);
        adminDetailsRepo.deleteById(adminID);
    }

    //deleting the admin record using email
    @Override
    public void deleteAdminDetailEmail(String email) {
        log.info("deleting admin details record for {}", email);
        adminDetailsRepo.deleteByEmail(email);

        //pass the email to helper class to delete app user instance
        appUtilsAdmin.dealWithAppUserDeleted(email);
    }

    // getting the admin record using email
    @Override
    public AdminDetails getAdminInfo(String email) {
        log.info("getting admin details record for {}", email);
        return adminDetailsRepo.getByEmail(email);
    }
}

/**
 * basic services for admin details
 * create record
 * get record
 * update record
 * delete record
 */