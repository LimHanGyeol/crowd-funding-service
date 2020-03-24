package me.hangyeol.crowdfunding.domain;


import me.hangyeol.crowdfunding.support.utils.PasswordHashUtil;
import me.hangyeol.crowdfunding.user.domain.User;
import me.hangyeol.crowdfunding.user.dto.UserDto;
import me.hangyeol.crowdfunding.user.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    private PasswordHashUtil passwordHashUtil;
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        passwordHashUtil = new PasswordHashUtil();
    }

    @Test
    public void loginTest() {
        User user = userService.findByEmail("dlagksruf19@naver.com");
        assertThat(user.toUserDto().getEmail(), is("dlagksruf19@naver.com"));
    }

    @Test
    public void metchPassword() {
        String convertPassword = passwordHashUtil.getSha256("1111");
        User user = new User("임한결","01043765678","dlagksruf19@naver.com","1111");
        assertTrue(user.matchPassword(convertPassword));
    }
}
