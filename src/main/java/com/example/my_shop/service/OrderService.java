package com.example.my_shop.service;

import com.example.my_shop.entity.model.Order;
import com.example.my_shop.entity.model.OrderDetails;
import com.example.my_shop.entity.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();

    Order save(Order order);

    List<Order> findOrderByUser(User user);














}
