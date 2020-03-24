package me.hangyeol.crowdfunding.support.utils;

import me.hangyeol.crowdfunding.user.dto.UserDto;

import javax.servlet.http.HttpSession;

public class HttpSessionUtil {
    public static final String USER_SESSION_KEY = "sessionUser";

    public static boolean isLoginUserSession(HttpSession session) {
        Object sessionUser = session.getAttribute(USER_SESSION_KEY);
        if (sessionUser == null) return false;
        return true;
    }

    public static UserDto.InfoRequest getUserSession(HttpSession session) {
        if (!isLoginUserSession(session)) return null;
        return (UserDto.InfoRequest) session.getAttribute(USER_SESSION_KEY);
    }
}
