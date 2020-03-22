package me.hangyeol.crowdfunding.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    private String name;
    private Long phoneNum;
    private String email;
    private String password;

}
