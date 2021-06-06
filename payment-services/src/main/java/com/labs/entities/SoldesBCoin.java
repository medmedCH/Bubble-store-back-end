package com.labs.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "Soldes")
public class SoldesBCoin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private String user_id;

    @NotNull
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;


    public SoldesBCoin(@NotNull BigDecimal  balance,@NotNull String user_id) {
        this.balance=balance;
        this.user_id=user_id;
    }



}
