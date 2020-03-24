package me.hangyeol.crowdfunding.user.service;

import me.hangyeol.crowdfunding.support.utils.PasswordHashUtil;
import me.hangyeol.crowdfunding.user.domain.User;
import me.hangyeol.crowdfunding.user.domain.UserRepository;
import me.hangyeol.crowdfunding.user.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordHashUtil passwordHashUtil;

    public UserService(UserRepository userRepository, PasswordHashUtil passwordHashUtil) {
        this.userRepository = userRepository;
        this.passwordHashUtil = passwordHashUtil;
    }

    public UserDto.InfoRequest join(UserDto.JoinRequest userDto) {
        if (userDto.getPassword().equals(userDto.getRePassword())) {
            String convertPassword = passwordHashUtil.getSha256(userDto.getPassword());
            userDto.setPassword(convertPassword);
        } // else 의 경우 Exception
        return userRepository.save(userDto.toEntity()).toUserDto();
    }

    public UserDto.InfoRequest login(String email, String password) {
        User user = findByEmail(email);
        String hashedPassword = passwordHashUtil.getSha256(password);
        if (!user.matchPassword(hashedPassword)) return null;
        return user.toUserDto();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }
}
