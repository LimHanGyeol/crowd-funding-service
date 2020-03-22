package me.hangyeol.crowdfunding.user.controller;

import me.hangyeol.crowdfunding.support.utils.HttpSessionUtil;
import me.hangyeol.crowdfunding.user.dto.UserDto;
import me.hangyeol.crowdfunding.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "join";
    }

    @PostMapping("/login")
    public String login(String email, String password, HttpSession session) {
        UserDto.InfoRequest user = userService.login(email, password);
        if (user == null) return "redirect:login";
        session.setAttribute(HttpSessionUtil.USER_SESSION_KEY, user);
        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(UserDto.JoinRequest userDto) {
        userService.join(userDto);
        return "login";
    }
}
