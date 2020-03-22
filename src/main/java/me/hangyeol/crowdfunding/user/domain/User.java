package me.hangyeol.crowdfunding.user.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private Long phoneNum;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public User(String name, Long phoneNum, String email, String password) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNum=" + phoneNum +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
