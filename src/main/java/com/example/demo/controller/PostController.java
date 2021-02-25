package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.Set;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post/{id}")
    public String showPostByID(@PathVariable("id") long id, Model model, HttpServletRequest request){
        Optional<Post> optionalPost = postService.findById(id);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            Set<Tag> tags = post.getTags();
            model.addAttribute("tags", tags);
            return "post_detail";
        }else {
            return "index";
        }

    }

}
