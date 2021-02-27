package com.example.demo.controller;

import com.example.demo.controller.request.SearchRequest;
import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.service.PostService;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class TagController {

    @Autowired
    private PostService postService;
    @Autowired
    private TagService tagService;

    @GetMapping("/tag/{id}")
    public String getPostByTag(@PathVariable("id") Long id, Model model){
        Optional<Tag> optionalTag = tagService.findById(id);
        model.addAttribute("searchRequest", new SearchRequest());
        if(optionalTag.isPresent()){
            Tag tag = optionalTag.get();
            List<Post> posts = tag.getPosts();
            model.addAttribute("posts", posts);
            return "postOfTag";

        }else {
            return "error";
        }
    }
}
