package com.example.my_shop.controller;

import com.example.my_shop.entity.model.Product;
import com.example.my_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;


@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;

    @RequestMapping(value = "/contacts")
    public String info() {
        return "contacts";
    }

    @GetMapping(value = "/")
    public String toShop (@RequestParam (name = "title", required = false) String title, Model model) {
        model.addAttribute("products", productService.findAll(title));
        return "main";
    }

        @GetMapping(value = "/remove/{id}")
        public String delete (@PathVariable("id") Integer id){
            productService.delete(id);
            return "redirect:/shop";
        }

    @GetMapping(value = "/search")
    public String search(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 5) Pageable pageable,
            @RequestParam String filter,
            Model model
    ) {
        Page<Product> page = productService.findByTitle( filter, pageable);
        int[] pagination = UtilController.computePagination(page);

        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/shop");
        model.addAttribute("page", page);

        return "menu";
    }
    @GetMapping(value = "/sport")
    public String listOfProducts (Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "shop_list";
    }

}











