package com.example.demo.controller;

import com.example.demo.controller.request.CommentRequest;
import com.example.demo.controller.request.SearchRequest;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping("/post/{id}")
    public String showPostByID(@PathVariable("id") long id, Model model, HttpServletRequest request){
        Optional<Post> optionalPost = postService.findById(id);
        Optional<User> optionalUser = userService.findbyEmail(userService.getUsername());
        model.addAttribute("searchRequest", new SearchRequest());
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            Set<Tag> tags = post.getTags();
            model.addAttribute("tags", tags);
            List<Comment> comments = post.getComments();
            model.addAttribute("comments", comments);

            // create new comment
            if(optionalUser.isPresent()){
                model.addAttribute("commentRequest", new CommentRequest(post.getId()));
                User user = optionalUser.get();
                model.addAttribute("user", user);
            } else{
                model.addAttribute("commentRequest", new CommentRequest());
            }
            return "post_detail";
        }else {
            return "error";
        }
    }

    @GetMapping("/posts/{id}")
    public String getAllPostOfUser(@PathVariable("id") long id, Model model){
        Optional<User> optionalUser = userService.findById(id);
        model.addAttribute("searchRequest", new SearchRequest());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            model.addAttribute("user", user);
            List<Post> postList = postService.getAllPostsByUserID(user.getId());
            System.out.println(postList.size());
            model.addAttribute("posts", postList);
            return "postsOfUser";
        }else {
            return "error";
        }
    }

    @GetMapping("/posts/N5/{category}")
    public String getAllPostsOfCategoryN5(@PathVariable("category") long category, Model model){
        List<Post> postList = postService.getAllPostN5ByTagId(category);
        model.addAttribute("searchRequest", new SearchRequest());
        model.addAttribute("posts", postList);
        return "postsOfCategory";
    }

    @GetMapping("/posts/N4/{category}")
    public String getAllPostsOfCategoryN4(@PathVariable("category") long category, Model model){
        List<Post> postList = postService.getAllPostN4ByTagId(category);
        model.addAttribute("searchRequest", new SearchRequest());
        model.addAttribute("posts", postList);
        return "postsOfCategory";
    }
    @GetMapping("/posts/N3/{category}")
    public String getAllPostsOfCategoryN3(@PathVariable("category") long category, Model model){
        List<Post> postList = postService.getAllPostN3ByTagId(category);
        model.addAttribute("searchRequest", new SearchRequest());
        model.addAttribute("posts", postList);
        return "postsOfCategory";
    }
    @GetMapping("/posts/N2/{category}")
    public String getAllPostsOfCategoryN2(@PathVariable("category") long category, Model model){
        List<Post> postList = postService.getAllPostN2ByTagId(category);
        model.addAttribute("searchRequest", new SearchRequest());
        model.addAttribute("posts", postList);
        return "postsOfCategory";
    }
    @GetMapping("/posts/N1/{category}")
    public String getAllPostsOfCategoryN1(@PathVariable("category") long category, Model model){
        List<Post> postList = postService.getAllPostN1ByTagId(category);
        model.addAttribute("searchRequest", new SearchRequest());
        model.addAttribute("posts", postList);
        return "postsOfCategory";
    }

}
