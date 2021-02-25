package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String homepage(Model model){
        List<Post> list = postService.findAll();
        model.addAttribute("listPost", list);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
