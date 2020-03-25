package me.hangyeol.crowdfunding.user.service;

import me.hangyeol.crowdfunding.support.utils.PasswordHashUtils;
import me.hangyeol.crowdfunding.user.domain.User;
import me.hangyeol.crowdfunding.user.domain.UserRepository;
import me.hangyeol.crowdfunding.user.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordHashUtils passwordHashUtils;

    public UserService(UserRepository userRepository, PasswordHashUtils passwordHashUtils) {
        this.userRepository = userRepository;
        this.passwordHashUtils = passwordHashUtils;
    }

    public UserDto.InfoRequest join(UserDto.JoinRequest userDto) {
        if (userDto.getPassword().equals(userDto.getRePassword())) {
            String convertPassword = passwordHashUtils.getSha256(userDto.getPassword());
            userDto.setPassword(convertPassword);
        } // else 의 경우 Exception
        return userRepository.save(userDto.toEntity()).toUserDto();
    }

    public UserDto.InfoRequest login(String email, String password) {
        User user = findByEmail(email);
        String hashedPassword = passwordHashUtils.getSha256(password);
        if (!user.matchPassword(hashedPassword)) return null;
        return user.toUserDto();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
