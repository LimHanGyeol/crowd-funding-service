package me.hangyeol.crowdfunding.user.controller;

import me.hangyeol.crowdfunding.support.utils.HttpSessionUtil;
import me.hangyeol.crowdfunding.user.dto.UserDto;
import me.hangyeol.crowdfunding.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<UserDto.InfoRequest> join(UserDto.JoinRequest userDto) {
        UserDto.InfoRequest user = userService.join(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto.InfoRequest> login(String email, String password, HttpSession session) {
        UserDto.InfoRequest loginUser = userService.login(email, password);
        session.setAttribute(HttpSessionUtil.USER_SESSION_KEY, loginUser);
        return ResponseEntity.ok().body(loginUser);
    }
}
