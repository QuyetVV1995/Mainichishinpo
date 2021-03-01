package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User save(UserDto registrationDto);
    Optional<User> findbyEmail(String email);

    String getUsername();

    Optional<User> findById(long id);

    List<User> findAll();

    User saveNewUser(User user);

    void deleteById(long id);
}