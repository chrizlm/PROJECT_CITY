package com.chris.cityparking.services;

import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.modules.AppUser;
import com.chris.cityparking.controllers.Forms.LogInForm;
import com.chris.cityparking.modules.Role;

import java.util.List;

public interface AppUserService {
    String userLogin(LogInForm logInForm);
    AppUser saveAppUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getAppUser(String userName);
    List<AppUser> getAppUsers();
    void changeUserPassword(EmailPasswordForm emailPasswordForm);

}

/**
 * required services for app user
 */