package com.zero.programmer.backend.cashier.controller;


import com.zero.programmer.backend.cashier.entity.Farmer;
import com.zero.programmer.backend.cashier.helper.Helper;
import com.zero.programmer.backend.cashier.model.FarmerRequest;
import com.zero.programmer.backend.cashier.model.ResponseData;
import com.zero.programmer.backend.cashier.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @GetMapping
    public ResponseEntity<?> listFarmer(){
        List<Farmer> farmers = farmerService.listFarmer();
        if (farmers == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData<>(404, List.of("Data Not Found"), null)
            );
        }
        return ResponseEntity.ok().body(
                new ResponseData<>(200, List.of("Success"), farmers)
        );
    }

    @GetMapping("/{farmerId}")
    public ResponseEntity<?> getFarmer(@PathVariable String farmerId){
        Long farmerIdLong = Helper.checkIdMustLong(farmerId);
        Farmer farmer = farmerService.getFarmer(farmerIdLong);
        if (farmer == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData<>(404, List.of("Data Not Found"), null)
            );
        }
        return ResponseEntity.ok().body(
                new ResponseData<>(200, List.of("Success"), farmer)
        );
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody FarmerRequest request, Errors errors){
        List<String> messages = new ArrayList<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                messages.add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseData<>(400, messages, null)
            );
        }
        Farmer farmer = farmerService.save(request);
        return ResponseEntity.ok().body(
                new ResponseData<>(200, List.of("Create Success"), farmer)
        );
    }

    @PutMapping("/{farmerId}")
    public ResponseEntity<?> update(@PathVariable String farmerId, @Valid @RequestBody FarmerRequest request, Errors errors){
        Long farmerIdLong = Helper.checkIdMustLong(farmerId);
        List<String> messages = new ArrayList<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                messages.add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseData<>(400, messages, null)
            );
        }
        Farmer farmer = farmerService.update(farmerIdLong, request);
        if(farmer == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData<>(404, List.of("Data Not Found"), null)
            );
        }
        return ResponseEntity.ok().body(
                new ResponseData<>(200, List.of("Update Success"), farmer)
        );
    }

    @DeleteMapping("/{farmerId}")
    public ResponseEntity<?> delete(@PathVariable String farmerId){
        Long farmerIdLong = Helper.checkIdMustLong(farmerId);
        boolean delete = farmerService.delete(farmerIdLong);
        if(!delete){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData<>(404, List.of("Data Not Found"), null)
            );
        }
        return ResponseEntity.ok().body(
            new ResponseData<>(200, List.of("Delete Success"), null)
        );
    }

}
