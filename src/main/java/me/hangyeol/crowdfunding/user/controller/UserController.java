package me.hangyeol.crowdfunding.user.controller;

import me.hangyeol.crowdfunding.user.domain.User;
import me.hangyeol.crowdfunding.user.domain.UserRepository;
import me.hangyeol.crowdfunding.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

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
        System.out.println("=====eamil : " + email + ", password : " + password);
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return "redirect:login";
        }

        session.setAttribute("user", user);

        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(User user) {
        System.out.println("=====User : " + user);
        userRepository.save(user);
        return "home";
    }
}
