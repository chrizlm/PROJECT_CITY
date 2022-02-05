package com.chris.cityparking.utils;

import com.chris.cityparking.modules.AppUser;
import com.chris.cityparking.modules.AttendantDetails;
import com.chris.cityparking.repositories.AppUserRepo;
import com.chris.cityparking.services.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppUtilsAttendant {
    @Autowired
    AppUserService appUserService;
    @Autowired
    AppUserRepo appUserRepo;

    //create an app user for attendant
    public void dealWithAppUser(AttendantDetails attendantDetails){
        AppUser appUser = new AppUser();
        appUser.setFirstName(attendantDetails.getFirstName());
        appUser.setEmail(attendantDetails.getEmail());
        appUser.setPassword(attendantDetails.getPassword());

        log.info("creating app user instance for {}", attendantDetails.getFirstName());
        //appUserRepo.save(appUser);
        appUserService.saveAppUser(appUser);
        appUserService.addRoleToUser(attendantDetails.getEmail(), "ROLE_ATTENDANT");
    }

    //update app user
    public void dealWithAppUserUpdate(AttendantDetails attendantDetails){
        AppUser user = appUserRepo.getByEmail(attendantDetails.getEmail());
        user.setFirstName(attendantDetails.getFirstName());
        user.setEmail(attendantDetails.getEmail());
        user.setPassword(attendantDetails.getPassword());
        log.info("updating app user instance for {}", attendantDetails.getFirstName());
        appUserRepo.save(user);
    }

    //delete app user
    public void dealWithAppUserDelete(String email){
        log.info("deleting app user instance for {}", email);
        appUserRepo.deleteByEmail(email);
    }
}

/**
 * helper class to handle creation and update of app user from attendant
 * and also delete app user instance
 */