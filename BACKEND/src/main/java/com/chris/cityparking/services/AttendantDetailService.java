package com.chris.cityparking.services;

import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.modules.AttendantDetails;

import java.util.List;

public interface AttendantDetailService {
    void createAttendantDetail(AttendantDetails attendantDetails);
    List<AttendantDetails> getAttendant(Long attendantID);
    List<AttendantDetails> getAllAttendants();
    void updateAttendant(AttendantDetails attendantDetails);
    void deleteAttendant(Long attendantID);
    void deleteAttendantEmail(String email);
    AttendantDetails getAttendantDetails(String email);
    void updateAttendantDetailPassword(EmailPasswordForm emailPasswordForm);
    List<AttendantDetails> getAttendantDetailsTwo(String email);

}
/**
 * required services for attendant
 */