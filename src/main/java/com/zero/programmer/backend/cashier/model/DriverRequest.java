package com.zero.programmer.backend.cashier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverRequest {

    @NotEmpty(message = "nama tidak boleh kosong")
    String name;

    String phoneNumber;
}
