package com.example.my_shop.entity.repository;

import com.example.my_shop.entity.model.Order;
import com.example.my_shop.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findOrderByUser(User user);

}

