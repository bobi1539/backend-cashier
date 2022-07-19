package com.zero.programmer.backend.cashier.service.impl;

import com.zero.programmer.backend.cashier.entity.Farmer;
import com.zero.programmer.backend.cashier.model.FarmerRequest;
import com.zero.programmer.backend.cashier.repository.FarmerRepository;
import com.zero.programmer.backend.cashier.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FarmerServiceImpl implements FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    @Override
    public List<Farmer> listFarmer() {
        List<Farmer> farmers = farmerRepository.findAll();
        if (farmers.isEmpty()) {
            return null;
        }
        return farmers;
    }

    @Override
    public Farmer getFarmer(Long farmerId) {
        Optional<Farmer> farmer = farmerRepository.findById(farmerId);
        if(farmer.isEmpty()){
            return null;
        }
        return farmer.get();
    }

    @Override
    public Farmer save(FarmerRequest request) {
        Farmer farmer = new Farmer();
        farmer.setName(request.getName());
        farmer.setTotalBon(request.getTotalBon());
        farmer.setPhoneNumber(request.getPhoneNumber());
        farmer.setDelete(false);
        farmer.setCreatedAt(new Date());
        farmer.setUpdatedAt(new Date());

        farmerRepository.save(farmer);
        return farmer;

    }

    @Override
    public Farmer update(Long farmerId, FarmerRequest request) {
        Optional<Farmer> farmer = farmerRepository.findById(farmerId);
        if(farmer.isEmpty()){
            return null;
        }
        Farmer update = farmer.get();
        update.setName(request.getName());
        update.setTotalBon(request.getTotalBon());
        update.setPhoneNumber(request.getPhoneNumber());
        update.setUpdatedAt(new Date());

        farmerRepository.save(update);
        return update;
    }

    @Override
    public boolean delete(Long farmerId) {
        Optional<Farmer> farmer = farmerRepository.findById(farmerId);
        if (farmer.isEmpty()){
            return false;
        }
        Farmer update = farmer.get();
        update.setDelete(true);
        farmerRepository.save(update);
        return true;
    }
}
