package com.example.my_shop.entity.repository;

import com.example.my_shop.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository< User, Integer> {

    User findUserByEmail (String email);

    User findByUsername(String username);//







}
