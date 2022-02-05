package com.chris.cityparking.repositories;

import com.chris.cityparking.modules.AttendantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendantDetailsRepo extends JpaRepository<AttendantDetails, Long> {
    List<AttendantDetails> getByAttendantID(Long attendantID);
    AttendantDetails getByEmail(String email);
    List<AttendantDetails> findByEmail(String email);
    void deleteByEmail(String email);
}

/**
 * repo for attendant
 */