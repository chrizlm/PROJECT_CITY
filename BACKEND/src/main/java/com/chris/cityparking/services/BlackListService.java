package com.chris.cityparking.services;

import com.chris.cityparking.modules.Blacklist;

import java.util.List;

public interface BlackListService {
    void save(Blacklist blacklist);
    List<Blacklist> getDefaulter(String numberPlate);
    List<Blacklist> getall();
    void delete(String numberPlate);
}
