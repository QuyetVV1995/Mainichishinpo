package com.example.demo.controller;

import com.example.demo.controller.request.SearchRequest;
import com.example.demo.entity.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private PostService postService;


    @PostMapping("/search")
    public String handleSearch(@ModelAttribute("searchRequest") SearchRequest searchRequest, Model model){
        List<Post> posts = postService.searchPost(searchRequest.getTerm(), 5, 0); // Dang tra ve null
        model.addAttribute("posts", posts);
        return "searchResult";
    }

    @GetMapping("/search/index")
    public String reindexFullText(Model model){
        postService.reindexFullText();
        return "redirect:/";
    }
}
