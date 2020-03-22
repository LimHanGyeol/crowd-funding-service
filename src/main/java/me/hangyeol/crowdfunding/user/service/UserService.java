package me.hangyeol.crowdfunding.user.service;

import me.hangyeol.crowdfunding.support.utils.PasswordHashUtil;
import me.hangyeol.crowdfunding.user.domain.User;
import me.hangyeol.crowdfunding.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordHashUtil passwordHashUtil;

    public void join(User user) {
        String convertPassword = passwordHashUtil.getSha256(user.getPassword());
        user.setPassword(convertPassword);
        userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        String hashedPassword = passwordHashUtil.getSha256(password);
        user.setPassword(hashedPassword);
        return user;
    }
}
