package com.zero.programmer.backend.cashier.controller;

import com.zero.programmer.backend.cashier.error.IdNotNumberException;
import com.zero.programmer.backend.cashier.model.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = IdNotNumberException.class)
    public ResponseEntity<?> idNotNumber(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseData<>(400, List.of("Id Must Be Number"), null)
        );
    }
}
