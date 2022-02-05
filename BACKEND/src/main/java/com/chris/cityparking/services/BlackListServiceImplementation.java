package com.chris.cityparking.services;

import com.chris.cityparking.modules.Blacklist;
import com.chris.cityparking.repositories.BlackListRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class BlackListServiceImplementation implements BlackListService{
    @Autowired
    BlackListRepo blackListRepo;

    @Override
    public void save(Blacklist blacklist) {
        log.info("saved record of defaulter");
       blackListRepo.save(blacklist);
    }

    @Override
    public List<Blacklist> getDefaulter(String numberPlate) {
        log.info("getting defaulter by number plate");
        return blackListRepo.getByNumberPlate(numberPlate);
    }

    @Override
    public List<Blacklist> getall() {
        log.info("getting all defaulters");
        return blackListRepo.findAll();
    }

    @Override
    public void delete(String numberPlate) {
        log.info("removing record of defaulter");
        blackListRepo.deleteByNumberPlate(numberPlate);
    }
}
