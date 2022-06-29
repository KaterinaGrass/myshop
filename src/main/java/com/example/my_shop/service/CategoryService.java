package com.example.my_shop.service;

import com.example.my_shop.entity.model.Category;


import java.util.List;

public interface CategoryService {

    List<Category> listCategory();

    void save(Category category);



}
