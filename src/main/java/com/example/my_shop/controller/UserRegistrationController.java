package com.example.my_shop.controller;

import com.example.my_shop.entity.model.User;
import com.example.my_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor

public class UserRegistrationController {

    private final UserService userService;

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping(value = "/registration")
    public String createUser(User user) {
        userService.createUser(user);
        return "redirect:/login";
    }
}
