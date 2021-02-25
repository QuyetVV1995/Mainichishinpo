package com.example.demo.service;

import com.example.demo.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    public List<Post> findAll();

    public Optional<Post> findById(long id);
}
