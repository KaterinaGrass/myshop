package com.example.my_shop.controller;

import com.example.my_shop.entity.model.Product;
import com.example.my_shop.entity.model.User;
import com.example.my_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ShoppingCartController {
    private final UserService userService;

    @GetMapping("/cart")
    public String getCart(Principal principal, Model model) {

        User userFromDB = userService.findByUsername(principal.getName());
        model.addAttribute("products", userFromDB.getProductList());

        return "cart";
    }

    @PostMapping("/ add")
    public String addToCart(
            @RequestParam("add") Product product, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        user.getProductList().add(product);
        userService.save(user);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(
            @RequestParam(value = "productId") Product product, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        if (product != null) {
            user.getProductList().remove(product);
        }
        userService.save(user);
        return "redirect:/cart";
    }

}
