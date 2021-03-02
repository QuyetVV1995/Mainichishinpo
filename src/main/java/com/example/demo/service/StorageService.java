package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    public void uploadFile(MultipartFile file);
}
