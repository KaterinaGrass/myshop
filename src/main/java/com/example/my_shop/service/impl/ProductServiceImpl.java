package com.example.my_shop.service.impl;

import com.example.my_shop.entity.model.Product;
import com.example.my_shop.entity.repository.ProductRepository;
import com.example.my_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.Optional;
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll(String title) {
        return productRepository.findAll();
    }

      @Override
    public Product getProductById(Integer id) {
          if (productRepository.findById(id).isPresent()) {
              return productRepository.findById(id).get();
          }
          return null;

    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product createNewProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }












}




