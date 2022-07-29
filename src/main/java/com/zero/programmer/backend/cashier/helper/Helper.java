package com.zero.programmer.backend.cashier.helper;

import com.zero.programmer.backend.cashier.error.IdNotNumberException;

public class Helper {

    public static Long checkIdMustLong(String id){
        Long idLong = 0L;
        try{
            return Long.parseLong(id);
        } catch (NumberFormatException e){
            throw new IdNotNumberException();
        }
    }
}
