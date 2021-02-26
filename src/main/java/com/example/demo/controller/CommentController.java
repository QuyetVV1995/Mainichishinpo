package com.example.demo.controller;

import com.example.demo.controller.request.CommentRequest;
import com.example.demo.entity.Comment;
import com.example.demo.entity.User;
import com.example.demo.exception.PostException;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CommentController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @PostMapping("/comment")
    public String handlePostComment(@ModelAttribute("comment") CommentRequest commentRequest) throws PostException {
        Optional<User> optionalUser = userService.findbyEmail(userService.getUsername());
        System.out.println("-----------");
        System.out.println(commentRequest);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            postService.addComment(commentRequest, user.getId());
            return "redirect:/post/" + commentRequest.getPost_id();
        }else{
            return "index";
        }

    }
}
