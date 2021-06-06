package com.labs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoldesBCoinDto {
        private Long id;
        private String user_id;
        private BigDecimal balance;
        private String imageusr ;

}