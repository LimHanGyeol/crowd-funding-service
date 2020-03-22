package me.hangyeol.crowdfunding.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import me.hangyeol.crowdfunding.user.dto.UserDto;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public boolean matchPassword(String inputPassword) {
        if (inputPassword == null) return false;
        return inputPassword.equals(password);
    }

    @Builder
    public User(String name, String phoneNum, String email, String password) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.password = password;
    }

    public UserDto.InfoRequest toUserDto() {
        return new UserDto.InfoRequest(name, phoneNum, email);
    }
}
