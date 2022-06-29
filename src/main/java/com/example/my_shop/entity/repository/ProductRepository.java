package com.example.my_shop.entity.repository;
import com.example.my_shop.entity.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByTitle (String title);

    Product findById(long id);

    @Query ("select p from Product p" )
    Page<Product> pageProduct (Pageable pageable);

    Page<Product> findByTitle(String title, Pageable pageable);



























}
