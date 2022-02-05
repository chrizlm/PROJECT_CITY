package com.chris.cityparking.utils;

import com.chris.cityparking.modules.AdminDetails;
import com.chris.cityparking.modules.AppUser;
import com.chris.cityparking.repositories.AppUserRepo;
import com.chris.cityparking.services.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppUtilsAdmin {
    @Autowired
    AppUserService appUserService;
    @Autowired
    AppUserRepo appUserRepo;

    //create an app user for admin
    public void dealWithAppUser(AdminDetails adminDetails){
        AppUser appUser = new AppUser();
        appUser.setFirstName(adminDetails.getFirstName());
        appUser.setEmail(adminDetails.getEmail());
        appUser.setPassword(adminDetails.getPassword());

        log.info("creating app user instance for {}", adminDetails.getFirstName());
        //appUserRepo.save(appUser);
        appUserService.saveAppUser(appUser);
        appUserService.addRoleToUser(adminDetails.getEmail(), "ROLE_ADMIN");

    }

    //update app user
    public void dealWithAppUserUpdate(AdminDetails adminDetails){
        AppUser user = appUserRepo.getByEmail(adminDetails.getEmail());
        user.setFirstName(adminDetails.getFirstName());
        user.setEmail(adminDetails.getEmail());
        user.setPassword(adminDetails.getPassword());
        log.info("updating app user instance for {}", adminDetails.getFirstName());
        appUserRepo.save(user);
    }

    //delete app user
    public void dealWithAppUserDeleted(String email){
        log.info("deleting app user instance for {}", email);
        appUserRepo.deleteByEmail(email);
    }
}

/**
 * helper class to handle creation and update of app user from admin
 * and also delete app user instance
 */