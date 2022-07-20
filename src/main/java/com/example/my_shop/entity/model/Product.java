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
    private BigDecimal price;
    @Column(length = 65555)
    private String description;
    @Column(length = 45)
    private String filename;

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;
   // @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
   // @JoinTable(name = "product_category",
           // joinColumns = @JoinColumn(name = "product_id"),
          //  inverseJoinColumns = @JoinColumn(name = "category_id"))
   // private List<Category> categories;



    @Transient
    public String getFilenameImagePath() {
        if (filename == null || id == null)
            return null;
        return "/product-filenames/"+ id + "/"+ filename;
    }
}
