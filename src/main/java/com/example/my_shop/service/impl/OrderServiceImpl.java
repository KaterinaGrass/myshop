package com.example.my_shop.service.impl;


import com.example.my_shop.entity.model.Order;
import com.example.my_shop.entity.model.User;
import com.example.my_shop.entity.repository.OrderRepository;
import com.example.my_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrderByUser(User user) {
        return orderRepository.findOrderByUser(user);
    }
}











