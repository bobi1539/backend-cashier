package com.zero.programmer.backend.cashier.controller;

import com.zero.programmer.backend.cashier.entity.Driver;
import com.zero.programmer.backend.cashier.model.DriverRequest;
import com.zero.programmer.backend.cashier.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping
    public ResponseEntity<?> listDriver(){
        List<Driver> drivers = driverService.listDriver();
        if(drivers == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<?> getDriver(@PathVariable Long driverId){
        Driver driver = driverService.driver(driverId);
        if(driver == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(driver);
    }

    @PostMapping()
    public ResponseEntity<?> createDriver(@Valid  @RequestBody DriverRequest request, Errors errors){
        List<String> messages = new ArrayList<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                messages.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(messages);
        }

        Driver driver = driverService.createDriver(request);
        return ResponseEntity.ok(driver);
    }

    @PutMapping("/{driverId}")
    public ResponseEntity<?> updateDriver(@PathVariable Long driverId, @Valid @RequestBody DriverRequest request, Errors errors){
        List<String> messages = new ArrayList<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                messages.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(messages);
        }
        Driver driver = driverService.updateDriver(driverId, request);
        if(driver == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(driver);

    }

    @DeleteMapping("/{driverId}")
    public ResponseEntity<?> deleteDriver(@PathVariable Long driverId){
        boolean deleteDriver = driverService.deleteDriver(driverId);
        if(deleteDriver){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
