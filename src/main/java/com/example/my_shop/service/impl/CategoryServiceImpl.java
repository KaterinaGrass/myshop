package com.example.my_shop.service.impl;

import com.example.my_shop.entity.model.Category;
import com.example.my_shop.entity.repository.CategoryRepository;
import com.example.my_shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final   CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategory() {
        return categoryRepository.findAll();
    }


    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }


}
