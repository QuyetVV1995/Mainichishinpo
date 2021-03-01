package com.example.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPostController {

    @GetMapping("/admin/manage-post")
    public String managePost(){
        return "manage_post";
    }
}
