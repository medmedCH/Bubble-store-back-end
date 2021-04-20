package com.labs.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;


    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "sales_counter")
    private Integer salesCounter;

    @NotNull
    @Column(name = "image_principale", nullable = false)
    private String imageprincupale;



    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "products_reviews",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "reviews_id"))
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;




    public Product(@NotNull String name, @NotNull String description,
                   @NotNull BigDecimal price,Integer quantity
                   ,Integer salesCounter, Set<Review> reviews, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.salesCounter = salesCounter;
        this.reviews = reviews;
        this.category = category;
    }
    public Product(@NotNull String name, @NotNull String description,
                   @NotNull BigDecimal price,Integer quantity
            ,Integer salesCounter, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.salesCounter = salesCounter;
        this.category = category;
    }
}