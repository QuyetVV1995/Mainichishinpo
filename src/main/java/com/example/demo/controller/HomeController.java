package com.example.demo.controller;

import com.example.demo.controller.request.SearchRequest;
import com.example.demo.entity.Post;
import com.example.demo.entity.Role;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.service.PostService;
import com.example.demo.service.TagService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;

    @GetMapping(value = {"/", "/{page}"})
    public String homepage(@PathVariable(value="page", required = false) Integer page, Model model){
        Optional<User> optionalUser = userService.findbyEmail(userService.getUsername());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("searchRequest", new SearchRequest());
        if(auth.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("ROLE_ADMIN"))){
            if (optionalUser.isPresent()) {

                User user = optionalUser.get();
                model.addAttribute("user", user);

                List<User> userList = userService.findAll();
                model.addAttribute("userList", userList);


//                long totalN1 = postService.getAllPostN1ByTagId(1).size();
//                long totalN2 = postService.getAllPostN2ByTagId(2).size();
//                long totalN3 = postService.getAllPostN3ByTagId(3).size();
//                long totalN4 = postService.getAllPostN4ByTagId(4).size();
//                long totalN5 = postService.getAllPostN5ByTagId(5).size();
//                long total_ITJapan = postService.getAllPostN5ByTagId(6).size();
//                long total_JavaBasic = postService.getAllPostN5ByTagId(7).size();
//                long total_SpringBoot = postService.getAllPostN5ByTagId(8).size();

//                model.addAttribute("totalN5", totalN5);
//                model.addAttribute("totalN4", totalN4);
//                model.addAttribute("totalN3", totalN3);
//                model.addAttribute("totalN2", totalN2);
//                model.addAttribute("totalN1", totalN1);


            }
           return "admin/index";
        }else {
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                model.addAttribute("user", user);
                if (page == null) {
                    page = 0;
                }
                Page<Post> pagePosts = postService.findAllPaging(page, 9); //Mỗi page 9 Post

                List<Post> posts = pagePosts.getContent();
                model.addAttribute("listPost", posts);
                //Sinh ra cấu trúc dữ liệu phân trang
                List<Paging> pagings = Paging.generatePages(page, pagePosts.getTotalPages());
                model.addAttribute("pagings", pagings);
            }
            return "index";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
