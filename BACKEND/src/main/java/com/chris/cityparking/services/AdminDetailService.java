package com.chris.cityparking.services;

import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.modules.AdminDetails;

public interface AdminDetailService {
    void createAdminDetail(AdminDetails adminDetails);
    void updateAdminDetail(AdminDetails adminDetails);
    void deleteAdminDetail(Long adminID);
    void deleteAdminDetailEmail(String email);
    AdminDetails getAdminInfo(String email);
    void updateAdminDetailNew(AdminDetails adminDetails);
    void updateAdminDetailPassword(EmailPasswordForm emailPasswordForm);
}

/**
 * required services for admin details
 */