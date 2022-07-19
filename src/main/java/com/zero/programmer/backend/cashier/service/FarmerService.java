package com.zero.programmer.backend.cashier.service;

import com.zero.programmer.backend.cashier.entity.Farmer;
import com.zero.programmer.backend.cashier.model.FarmerRequest;

import java.util.List;

public interface FarmerService {
    List<Farmer> listFarmer();

    Farmer getFarmer(Long farmerId);

    Farmer save(FarmerRequest request);

    Farmer update(Long farmerId, FarmerRequest request);

    boolean delete(Long farmerId);
}
