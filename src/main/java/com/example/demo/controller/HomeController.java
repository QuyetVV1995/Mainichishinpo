package com.example.demo.controller;

import com.example.demo.controller.request.SearchRequest;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping(value = {"/", "/{page}"})
    public String homepage(@PathVariable(value="page", required = false) Integer page, Model model){
        Optional<User> optionalUser = userService.findbyEmail(userService.getUsername());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            model.addAttribute("user", user);
        }

        if (page == null) {
            page = 0;
        }
        Page<Post> pagePosts = postService.findAllPaging(page, 9); //Mỗi page 9 Post

        List<Post> posts = pagePosts.getContent();
        model.addAttribute("listPost", posts);
        //Sinh ra cấu trúc dữ liệu phân trang
        List<Paging> pagings = Paging.generatePages(page, pagePosts.getTotalPages());
        model.addAttribute("pagings", pagings);
        model.addAttribute("searchRequest", new SearchRequest());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
