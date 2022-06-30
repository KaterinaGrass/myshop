package com.example.my_shop.controller;

import com.example.my_shop.entity.model.Category;
import com.example.my_shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping(value = "/categories/new")
    public String showCategoryNewForm (Model model) {
        model.addAttribute("category", new Category());
        return "category_form";
    }


    @PostMapping(value = "/categories/save")
    public String saveCategory(Category category) {
        categoryService.save(category);
        return "redirect:/categories";

    }

    @GetMapping(value = "/categories")
    private String listCategories(Model model) {
    List<Category> listCategories = categoryService.listCategory();
    model.addAttribute("listCategories", listCategories);
    return "categories";
    }





}
