package com.zero.programmer.backend.cashier.controller;

import com.zero.programmer.backend.cashier.entity.Driver;
import com.zero.programmer.backend.cashier.error.IdNotNumberException;
import com.zero.programmer.backend.cashier.helper.Helper;
import com.zero.programmer.backend.cashier.model.DriverRequest;
import com.zero.programmer.backend.cashier.model.ResponseData;
import com.zero.programmer.backend.cashier.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData<>(404, List.of("Data Not Found"), null)
            );
        }
        return ResponseEntity.ok().body(
                new ResponseData<>(200, List.of("Success"), drivers)
        );
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<?> getDriver(@PathVariable String driverId){
        Long driverIdLong = Helper.checkIdMustLong(driverId);
        Driver driver = driverService.driver(driverIdLong);
        if(driver == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData<>(404, List.of("Data Not Found"), null)
            );
        }
        return ResponseEntity.ok().body(
                new ResponseData<>(200, List.of("Success"), driver)
        );
    }

    @PostMapping()
    public ResponseEntity<?> createDriver(@Valid  @RequestBody DriverRequest request, Errors errors){
        List<String> messages = new ArrayList<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                messages.add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseData<>(400, messages, null)
            );
        }

        Driver driver = driverService.createDriver(request);
        return ResponseEntity.ok().body(
                new ResponseData<>(200, List.of("Create Success"), driver)
        );
    }

    @PutMapping("/{driverId}")
    public ResponseEntity<?> updateDriver(@PathVariable String driverId, @Valid @RequestBody DriverRequest request, Errors errors){
        Long driverIdLong = Helper.checkIdMustLong(driverId);
        List<String> messages = new ArrayList<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                messages.add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseData<>(400, messages, null)
            );
        }
        Driver driver = driverService.updateDriver(driverIdLong, request);
        if(driver == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData<>(404, List.of("Data Not Found"), null)
            );
        }
        return ResponseEntity.ok().body(
                new ResponseData<>(200, List.of("Update Success"), driver)
        );

    }

    @DeleteMapping("/{driverId}")
    public ResponseEntity<?> deleteDriver(@PathVariable String driverId){
        Long driverIdLong = Helper.checkIdMustLong(driverId);
        boolean deleteDriver = driverService.deleteDriver(driverIdLong);
        if(deleteDriver){
            return ResponseEntity.ok().body(
                    new ResponseData<>(200, List.of("Delete Success"), null)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseData<>(404, List.of("Data Not Found"), null)
        );
    }
}
