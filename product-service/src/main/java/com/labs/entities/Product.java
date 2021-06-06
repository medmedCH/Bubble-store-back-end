package com.labs.entities;

import com.labs.entities.enums.ProductDevise;
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
@ToString(callSuper = true)
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;
    @NotNull
    @Column(name = "bubblecoin", precision = 10, scale = 2, nullable = false)
    private BigDecimal bubblecoin;

    @Column(name = "quantity")
    private Integer quantity;

    @NotNull
    @Column(name = "imageprincipale", nullable = false)
    private String imgpr;
    @NotNull
    @Column(name = "imagess1", nullable = false)
    public String images1;
    @Column(name = "imagess2", nullable = false)
    public String images2;
    @Column(name = "imagess3", nullable = false)
    public String images3;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "devise", nullable = false)
    private ProductDevise devise;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "products_reviews",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "reviews_id"))
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Product(@NotNull String title, @NotNull String description,
                   @NotNull BigDecimal price,@NotNull BigDecimal bubblecoin,Integer quantity,@NotNull String imgpr,String images1,String images2, String images3,ProductDevise devise,Category category) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.bubblecoin = bubblecoin;
        this.quantity = quantity;
        this.imgpr = imgpr ;
        this.images1=images1;
        this.images2=images2;
        this.images3=images3;
        this.devise=devise;
        this.category = category;
    }

    public Product(Long id, String title, String description, BigDecimal price, Integer quantity, String imgpr, String images1, String images2, String images3, ProductDevise devise, Set<Review> reviews, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.imgpr = imgpr;
        this.images1 = images1;
        this.images2 = images2;
        this.images3 = images3;
        this.devise = devise;
        this.reviews = reviews;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImgpr() {
        return imgpr;
    }

    public void setImgpr(String imgpr) {
        this.imgpr = imgpr;
    }
}