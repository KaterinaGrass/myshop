package com.example.my_shop.service;


import com.example.my_shop.entity.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

 List<Product> findAll (String title);


Product getProductById(Integer id);


  Optional<Product> findById(Integer id);

  Product createNewProduct(Product product);

  void delete(Integer id);

  void saveProduct (Product product);

 List<Product> findAll ();








}


















