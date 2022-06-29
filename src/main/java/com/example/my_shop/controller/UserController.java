package com.example.my_shop.controller;

import com.example.my_shop.entity.enam.Role;
import com.example.my_shop.entity.model.Category;
import com.example.my_shop.entity.model.Product;
import com.example.my_shop.entity.model.User;
import com.example.my_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping ("/users")
public class UserController {

  private final UserService userService;

  @GetMapping
  public String userList(Model model) {
    List<User> userList = userService.findAll();
    model.addAttribute("userList", userList);
    return "users";
  }


  @PostMapping("/saveUser")
  public String saveUser(@ModelAttribute("user") User user) {
    userService.save(user);
    return "redirect:/users";
  }


  @GetMapping(value = "/editUser/{id}")
  public String showEditProductPage(@PathVariable(name = "id") Integer id, Model model) {
    User user = userService.getUserById(id);
    model.addAttribute("user", user);
    model.addAttribute("roles", Role.values());
    return "edit_user";


  }
}






