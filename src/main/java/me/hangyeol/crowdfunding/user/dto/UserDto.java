package me.hangyeol.crowdfunding.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import me.hangyeol.crowdfunding.user.domain.User;


public abstract class UserDto {

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class JoinRequest {
        private String name;
        private String phoneNum;
        private String email;
        private String password;
        private String rePassword;

        public User toEntity() {
            return User.builder()
                    .name(name)
                    .phoneNum(phoneNum)
                    .email(email)
                    .password(password)
                    .build();
        }

    }
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class InfoRequest {
        private String name;
        private String phoneNum;
        private String email;

        public InfoRequest(String name, String phoneNum, String email) {
            this.name = name;
            this.phoneNum = phoneNum;
            this.email = email;
        }
    }
}
