package com.example.demo.controller;

import com.example.demo.controller.request.SearchRequest;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homepage(Model model){
        Optional<User> optionalUser = userService.findbyEmail(userService.getUsername());
        User user = optionalUser.get();
        List<Post> list = postService.findAll();
        model.addAttribute("listPost", list);
        model.addAttribute("user", user);
        model.addAttribute("searchRequest", new SearchRequest());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
