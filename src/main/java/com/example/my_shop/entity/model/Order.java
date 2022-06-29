package com.example.my_shop.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private LocalDate orderDate;
    @Column
    private String shippingMethod;
    @Column
    private BigDecimal totalPrice;
    @Column
    @NotBlank(message = "the address cannot be empty")
    private String firstName;
    @NotBlank(message = "the address cannot be empty")
    private String lastName;
    @Column
    @NotBlank(message = "the address cannot be empty")
    private String address;
    @Column
    @NotBlank(message = "the payment cannot be empty")
    private String payment;

    @ManyToMany
    @JoinTable(name = "orders_product",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

   @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;

   public Order(User user) {
        this.orderDate = LocalDate.now();
        this.user = user;
        this.products = new ArrayList<>();
    }










}
