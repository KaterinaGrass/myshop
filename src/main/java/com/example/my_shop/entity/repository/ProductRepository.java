package com.example.my_shop.entity.repository;
import com.example.my_shop.entity.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.math.BigDecimal;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByTitle (String title);

    Product findById(long id);

    Page<Product> findByTitle(String productTitle, Pageable pageable);


    Page<Product> findAll(Pageable pageable);

    Page<Product> findByCategoryId(Integer categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.title LIKE %?1%" + " OR p.title LIKE %?1%" + " OR p.description LIKE %?1%")
    public Page<Product> search(String searchKey, Pageable pageable);

   // Page<Product> findByPriceBetween(Integer startingPrice, Integer endingPrice, Pageable pageable);

   // @Query(value = "SELECT min(price) FROM Product ")
   // BigDecimal minProductPrice();

   // @Query(value = "SELECT max(price) FROM Product ")
  //  BigDecimal maxProductPrice();
}
