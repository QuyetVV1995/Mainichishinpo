package com.example.demo.service;

import com.example.demo.entity.Tag;

import java.util.Optional;

public interface TagService {

    public Optional<Tag> findById(Long id);
}
