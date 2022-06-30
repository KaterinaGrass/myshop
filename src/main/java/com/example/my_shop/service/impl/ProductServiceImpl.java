package com.example.my_shop.service.impl;

import com.example.my_shop.entity.model.Category;
import com.example.my_shop.entity.model.Product;
import com.example.my_shop.entity.repository.ProductRepository;
import com.example.my_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

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
    public void saveProduct(Product product, Category category) {
        Product newProduct = new Product();
        newProduct.setCategory(category);
        newProduct.setPrice(product.getPrice());
        newProduct.setTitle(product.getTitle());
        newProduct.setDescription(newProduct.getDescription());

        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findByTitle(String productTitle, Pageable pageable) {
        return productRepository.findByTitle(productTitle, pageable);
    }


}





