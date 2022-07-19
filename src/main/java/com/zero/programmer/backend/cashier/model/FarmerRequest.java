package com.zero.programmer.backend.cashier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmerRequest {

    @NotEmpty(message = "Nama tidak boleh kosong")
    private String name;

    private Long totalBon;

    private String phoneNumber;

}
