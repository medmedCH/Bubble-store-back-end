package com.labs.service;

import com.labs.dto.SoldesBCoinDto;

public class Bubblecoinservices {


    /*public SoldesBCoin create(String user_id) {

    }*/

    public static SoldesBCoinDto mapToDto(SoldesBCoin soldesBCoin) {
        return new SoldesBCoinDto(
                soldesBCoin.getId(),
                soldesBCoin.getUser_id(),
                soldesBCoin.getBalance()
        );
    }
}
