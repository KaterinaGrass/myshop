package com.example.my_shop.controller;

import com.example.my_shop.entity.model.Category;
import com.example.my_shop.entity.model.Product;
import com.example.my_shop.service.CategoryService;
import com.example.my_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import  org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping(value = "/products")
    public String listOfProducts (Model model) {
       List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping(value = "/new")
    public String showNewProductForm(Model model) {
        List<Category> listCategories = categoryService.listCategory();
        model.addAttribute("product", new Product());
        model.addAttribute("listCategories", listCategories);
        return "products_new";
    }

    @PostMapping(value = "/save")
    public String saveProduct(Product product)  {
       // product.setCategory(productService.getProductById(product.getId()).getCategory());
      //  productService.saveProduct(product,category);
        productService.createNewProduct(product);
        return "redirect:/products";
  }

    @GetMapping(value = "/edit/{id}")
    public String showEditProductPage (@PathVariable (name = "id") Integer id, Model model) {
        Product product = productService.findById(id).get();
        model.addAttribute("product", product);
        List<Category> listCategories = categoryService.listCategory();
        model.addAttribute("listCategories", listCategories);
        return "edit_product";

    }

    @GetMapping(value = "/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Integer id) {
        productService.delete(id);
        return "redirect:/products";
    }


    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") Product product, Model model) {
        model.addAttribute("product", product);
        return "product";
    }

}
