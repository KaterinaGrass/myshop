package com.example.my_shop.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String title;
    @Column
    private int quantity;
    @Column
    private BigDecimal price;
    @Column(length = 65555)
    private String description;

    @Column(length = 45, nullable = true)//
    private String filename;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @Transient
    public String getFilenameImagePath() {
        if (filename == null || id == null)
            return null;
        return "/product-filenames/"+ id + "/"+ filename;
    }





}
