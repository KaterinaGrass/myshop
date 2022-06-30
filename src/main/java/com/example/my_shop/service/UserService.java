package com.example.my_shop.service;

import com.example.my_shop.entity.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

     boolean createUser(User user);

    List<User> findAll();

    User findByUsername(String username);

    User save(User user);

    Optional<User> findUserById (Integer id);
    User getUserById (Integer id) ;

    void delete(Integer id);

}





