package me.hangyeol.crowdfunding.user.controller;

import me.hangyeol.crowdfunding.user.dto.UserDto;
import me.hangyeol.crowdfunding.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserDto.InfoRequest> join(@Valid UserDto.JoinRequest userDto) {
        UserDto.InfoRequest user = userService.join(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto.InfoRequest> login(String email, String password) {
        UserDto.InfoRequest loginUser = userService.login(email, password);
        return ResponseEntity.ok().body(loginUser);
    }
}
