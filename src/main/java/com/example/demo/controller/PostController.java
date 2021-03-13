package com.example.demo.controller;

import com.example.demo.controller.request.CommentRequest;
import com.example.demo.controller.request.PostRequest;
import com.example.demo.controller.request.SearchRequest;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.service.PostService;
import com.example.demo.service.StorageService;
import com.example.demo.service.TagService;
import com.example.demo.service.UserService;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    @Autowired
    private TagService tagService;
    @Autowired
    private StorageService storageService;

    @GetMapping("/post/{id}")
    public String showPostByID(@PathVariable("id") long id, Model model, HttpServletRequest request){
        Optional<Post> optionalPost = postService.findById(id);
        Optional<User> optionalUser = userService.findbyEmail(userService.getUsername());
        model.addAttribute("searchRequest", new SearchRequest());
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            post.setContent(markdownToHTML(post.getContent()));
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
                model.addAttribute("user", new User());
                model.addAttribute("commentRequest", new CommentRequest());
            }
            return "post_detail";
        }else {
            return "error";
        }
    }

    // Lay tat ca cac bai viet cua User
    @GetMapping("/posts/{id}")
    public String getAllPostOfUser(@PathVariable("id") long id, Model model){
        Optional<User> optionalUser = userService.findById(id);
        model.addAttribute("searchRequest", new SearchRequest());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            model.addAttribute("user", user);
            List<Post> postList = postService.getAllPostsByUserID(user.getId());
            for (Post post:postList){
                post.setContent(markdownToHTML(post.getContent()));
            }
            model.addAttribute("posts", postList);
            return "postsOfUser";
        }else {
            return "error";
        }
    }

    @GetMapping("/posts/{category}/{tag_id}")
    public String getAllPostsOfCategory(@PathVariable("tag_id") long tag_id, @PathVariable("category") String category, Model model){
        User user = userService.findbyEmail(userService.getUsername()).get();
        model.addAttribute("user", user);
        List<Post> postList = postService.getAllPostByTagId(category, tag_id);
        model.addAttribute("searchRequest", new SearchRequest());
        for (Post post:postList){
            post.setContent(markdownToHTML(post.getContent()));
        }
        model.addAttribute("posts", postList);
        return "postsOfCategory";
    }

    @GetMapping("/post/create-post")
    public String createPost(Model model){
        Optional<User> optionalUser = userService.findbyEmail(userService.getUsername());
        User user = optionalUser.get();
        model.addAttribute("user", user);
        model.addAttribute("searchRequest", new SearchRequest());
        model.addAttribute("newPost", new PostRequest());
        List<Tag> tagList = tagService.findAll();
        model.addAttribute("tagList", tagList);
        return "/create_post";
    }

    @PostMapping(value = "/post/save-post")
    public String createPostHandle(@ModelAttribute("newPost") PostRequest postRequest ){
        Optional<User> optionalUser = userService.findbyEmail(userService.getUsername());
        User user = optionalUser.get();
        storageService.uploadFiles(postRequest.getFile());
        Post post = new Post(postRequest.getTitle(),"", postRequest.getTags());
        post.setContent(markdownToHTML(postRequest.getContent()));
        post.setUser(user);
        post.setId(postRequest.getId());
        postService.save(post);
        return "redirect:/";
    }



    @GetMapping("/posts/edit/{id}")
    public String editPost(@PathVariable("id") long id, Model model){
        Optional<User> optionalUser = userService.findbyEmail(userService.getUsername());
        User user = optionalUser.get();
        model.addAttribute("user", user);
        model.addAttribute("searchRequest", new SearchRequest());
        Post post = postService.findById(id).get();
        PostRequest postRequest = new PostRequest(post.getId(), post.getTitle(), post.getContent(), post.getTags());
        postRequest.setFile(null);
        model.addAttribute("newPost", postRequest);
        List<Tag> tagList = tagService.findAll();
        model.addAttribute("tagList", tagList);

        return "edit_post_owner";
    }

    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable("id") long id){
        postService.deleteByID(id);
        return "redirect:/";
    }




    private String markdownToHTML(String markdown) {
        Parser parser = Parser.builder()
                .build();

        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .build();

        return renderer.render(document);
    }
}
