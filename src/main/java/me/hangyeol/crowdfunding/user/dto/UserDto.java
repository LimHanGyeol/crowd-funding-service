package me.hangyeol.crowdfunding.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import me.hangyeol.crowdfunding.user.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public abstract class UserDto {

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "메일 양식을 지켜주세요.")
    private String email;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "[0-1]{3}[0-9]{4}[0-9]{4}", message = "전화번호는 -을 제외하고 입력해주세요.")
    private String phoneNum;

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class JoinRequest extends UserDto {

        private String password;
        private String rePassword;

        public User toEntity() {
            return User.builder()
                    .name(super.name)
                    .phoneNum(super.phoneNum)
                    .email(super.email)
                    .password(password)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class InfoRequest extends UserDto {

        private Long id;

        public InfoRequest(Long id, String name, String phoneNum, String email) {
            this.id = id;
            super.name = name;
            super.phoneNum = phoneNum;
            super.email = email;
        }
    }
}
