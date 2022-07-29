package com.zero.programmer.backend.cashier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {
    private int code;
    private List<String> message = new ArrayList<>();
    private T data;
}
