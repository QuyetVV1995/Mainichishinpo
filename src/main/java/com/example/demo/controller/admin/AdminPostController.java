package com.example.demo.controller.admin;

import com.example.demo.controller.request.SearchRequest;
import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.service.PostService;
import com.example.demo.service.TagService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminPostController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private TagService tagService;

    @GetMapping("/manage-post")
    public String managePost(Model model){
        Optional<User> optionalUser = userService.findbyEmail(userService.getUsername());
        User user = optionalUser.get();
        model.addAttribute("user", user);
        model.addAttribute("searchRequest", new SearchRequest());
        List<Post> postList = postService.findAll();
        model.addAttribute("postList", postList);
        return "admin/manage_post";
    }

    @GetMapping("/post/create-post")
    public String createPost(Model model){
        Optional<User> optionalUser = userService.findbyEmail(userService.getUsername());
        User user = optionalUser.get();
        model.addAttribute("user", user);
        model.addAttribute("searchRequest", new SearchRequest());
        model.addAttribute("newPost", new Post());
        List<Tag> tagList = tagService.findAll();
        model.addAttribute("tagList", tagList);
        return "/admin/create_post";
    }

    @PostMapping("/post/save-post")
    public String createPostHandle(@ModelAttribute("newPost") Post post){
        Optional<User> optionalUser = userService.findbyEmail(userService.getUsername());
        User user = optionalUser.get();
        post.setUser(user);
        postService.save(post);
        return "redirect:/admin/manage-post";
    }
}
