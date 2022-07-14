package com.zero.programmer.backend.cashier.service;

import com.zero.programmer.backend.cashier.entity.Driver;
import com.zero.programmer.backend.cashier.model.DriverRequest;

import java.util.List;

public interface DriverService {
    List<Driver> listDriver();

    Driver driver(Long driverId);

    Driver createDriver(DriverRequest request);

    Driver updateDriver(Long driverId, DriverRequest request);

    boolean deleteDriver(Long driverId);
}
