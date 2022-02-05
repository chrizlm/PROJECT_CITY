package com.chris.cityparking.repositories;

import com.chris.cityparking.modules.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlackListRepo extends JpaRepository<Blacklist, Long> {
    List<Blacklist> getByNumberPlate(String numberPlate);
    void deleteByNumberPlate(String numberPlate);
}
