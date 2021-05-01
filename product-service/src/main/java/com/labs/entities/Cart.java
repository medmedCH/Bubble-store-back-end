package com.labs.entities;

import com.labs.entities.enums.CartStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "carts")
public class Cart  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private String user_id;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CartStatus status;


    public Cart(@NotNull String user_id, @NotNull CartStatus status) {
        this.user_id = user_id;
        this.status = status;
    }

}

