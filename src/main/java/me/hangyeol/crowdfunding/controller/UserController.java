package me.hangyeol.crowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "join";
    }

    @PostMapping("/login")
    public String login(String email, String password) {
        System.out.println("=====eamil : " + email + ", password : " + password);
        return "home";
    }

    @PostMapping
    public String join(String email, String password) {
        System.out.println("=====eamil : " + email + ", password : " + password);
        return "home";
    }
}
