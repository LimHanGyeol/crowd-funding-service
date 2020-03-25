package me.hangyeol.crowdfunding.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import me.hangyeol.crowdfunding.user.domain.User;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


public abstract class UserDto {

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class JoinRequest {
        @NotBlank(message = "이름을 입력해주세요.")
        private String name;

        @NotBlank(message = "전화번호를 입력해주세요.")
        @Pattern(regexp = "[0-1]{3}[0-9]{4}[0-9]{4}", message = "전화번호는 -을 제외하고 입력해주세요.")
        private String phoneNum;

        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "메일 양식을 지켜주세요.")
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
        private Long id;
        private String name;
        private String phoneNum;
        private String email;

        public InfoRequest(Long id, String name, String phoneNum, String email) {
            this.id = id;
            this.name = name;
            this.phoneNum = phoneNum;
            this.email = email;
        }
    }
}
