package com.example.demo.controller.admin;

import com.example.demo.controller.request.SearchRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/manage-user")
    public String manageUser(Model model){

        Optional<User> optionalUser = userService.findbyEmail(userService.getUsername());
        User user = optionalUser.get();
        model.addAttribute("user", user);
        List<User> userList = userService.findAll();
        model.addAttribute("searchRequest", new SearchRequest());
        model.addAttribute("userList", userList);
        return "admin/manage_user";
    }

    @GetMapping("/admin/create-user")
    public String createUser(Model model){
        model.addAttribute("newUser", new User());
        return "admin/create_user";
    }

    @PostMapping("/admin/create-user")
    public String createUserHandle(@ModelAttribute("newUser") User user){

        userService.saveNewUser(user);

        return "redirect:/admin/manage-user";
    }
}
