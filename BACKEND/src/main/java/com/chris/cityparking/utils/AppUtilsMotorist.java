package com.chris.cityparking.utils;

import com.chris.cityparking.modules.AppUser;
import com.chris.cityparking.modules.MotoristDetails;
import com.chris.cityparking.repositories.AppUserRepo;
import com.chris.cityparking.services.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppUtilsMotorist {
    @Autowired
    PasswordEncoder passwordEncoder;
     @Autowired
     AppUserService appUserService;
    @Autowired
    AppUserRepo appUserRepo;

    //create an app user for motorist
    public void dealWithAppUser(MotoristDetails motoristDetails){
        AppUser appUser = new AppUser();
        appUser.setFirstName(motoristDetails.getFirstName());
        appUser.setEmail(motoristDetails.getEmail());
        appUser.setPassword(motoristDetails.getPassword());

        log.info("creating app user instance for {}", motoristDetails.getFirstName());
        //appUserRepo.save(appUser);
        appUserService.saveAppUser(appUser);
        appUserService.addRoleToUser(motoristDetails.getEmail(), "ROLE_MOTORIST");
    }

    //update app user
    public void dealWithAppUserUpdate(MotoristDetails motoristDetails){
        AppUser user = appUserRepo.getByEmail(motoristDetails.getEmail());
        user.setFirstName(motoristDetails.getFirstName());
        user.setEmail(motoristDetails.getEmail());
        user.setPassword(motoristDetails.getPassword());
        log.info("updating app user instance for {}", motoristDetails.getFirstName());
        appUserRepo.save(user);
    }

    //delete app user
    public void dealWithAppUserDelete(String email){
        log.info("deleting app user instance for {}", email);
        appUserRepo.deleteByEmail(email);
    }
}

/**
 * helper class to handle creation and update of app user from motorist
 * and also delete app user instance
 */