package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserDto registrationDto);
}