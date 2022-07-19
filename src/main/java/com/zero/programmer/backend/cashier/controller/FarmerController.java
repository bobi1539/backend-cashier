package com.zero.programmer.backend.cashier.controller;


import com.zero.programmer.backend.cashier.entity.Farmer;
import com.zero.programmer.backend.cashier.model.FarmerRequest;
import com.zero.programmer.backend.cashier.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
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
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(farmers);
    }

    @GetMapping("/{farmerId}")
    public ResponseEntity<?> getFarmer(@PathVariable Long farmerId){
        Farmer farmer = farmerService.getFarmer(farmerId);
        if (farmer == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(farmer);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody FarmerRequest request, Errors errors){
        List<String> messages = new ArrayList<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                messages.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(messages);
        }
        Farmer farmer = farmerService.save(request);
        return ResponseEntity.ok(farmer);
    }

    @PutMapping("/{farmerId}")
    public ResponseEntity<?> update(@PathVariable Long farmerId, @Valid @RequestBody FarmerRequest request, Errors errors){
        List<String> messages = new ArrayList<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                messages.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(messages);
        }
        Farmer farmer = farmerService.update(farmerId, request);
        if(farmer == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(farmer);
    }

    @DeleteMapping("/{farmerId}")
    public ResponseEntity<?> delete(@PathVariable Long farmerId){
        boolean delete = farmerService.delete(farmerId);
        if(!delete){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Data berhasil dihapus");
    }

}
