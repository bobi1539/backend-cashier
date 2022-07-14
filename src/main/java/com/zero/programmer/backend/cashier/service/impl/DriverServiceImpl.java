package com.zero.programmer.backend.cashier.service.impl;

import com.zero.programmer.backend.cashier.entity.Driver;
import com.zero.programmer.backend.cashier.model.DriverRequest;
import com.zero.programmer.backend.cashier.repository.DriverRepository;
import com.zero.programmer.backend.cashier.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepository driverRepository;

    @Override
    public List<Driver> listDriver() {
        List<Driver> drivers = driverRepository.findAll();
        if(drivers.isEmpty()){
            return null;
        }
        return drivers;
    }

    @Override
    public Driver driver(Long driverId) {
        Optional<Driver> driver = driverRepository.findById(driverId);
        return driver.orElse(null);
    }

    @Override
    public Driver createDriver(DriverRequest request) {
        Driver driver = new Driver();
        driver.setName(request.getName());
        driver.setPhoneNumber(request.getPhoneNumber());
        driver.setDelete(false);
        driver.setCreatedAt(new Date());
        driver.setUpdatedAt(new Date());

        driverRepository.save(driver);
        return driver;
    }

    @Override
    public Driver updateDriver(Long driverId, DriverRequest request) {
        if(isDriverNotExist(driverId)){
            return null;
        }
        Driver driver = driver(driverId);
        driver.setName(request.getName());
        driver.setPhoneNumber(request.getPhoneNumber());
        driver.setUpdatedAt(new Date());
        driverRepository.save(driver);
        return driver;
    }

    @Override
    public boolean deleteDriver(Long driverId) {
        if(isDriverNotExist(driverId)){
            return false;
        }
        Driver driver = driver(driverId);
        driver.setDelete(true);
        driverRepository.save(driver);
        return true;
    }

    private boolean isDriverNotExist(Long id){
        return !driverRepository.findById(id).isPresent();
    }
}
