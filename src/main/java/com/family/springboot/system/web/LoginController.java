package com.family.springboot.system.web;

import com.family.springboot.system.dto.UserDto;
import com.family.springboot.system.service.LoginService;
import com.family.springboot.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/loginController")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(String userName, String passWord, HttpServletRequest request, HttpServletResponse response) {
        String loginState = null;
        try {
            loginState = loginService.login(userName, passWord);
            if ("".equals(loginState)) {
                UserDto userDto = userService.getUser(userName);
                //创建cookie
                Cookie cookie = new Cookie("userName", userDto.getUserName());
                Cookie cookie2 = new Cookie("userId", userDto.getUserId());
                cookie.setMaxAge(24 * 60 * 60);
                cookie.setPath("/");
                cookie2.setMaxAge(24 * 60 * 60);
                cookie2.setPath("/");
                response.addCookie(cookie);
                response.addCookie(cookie2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginState;
    }
}
