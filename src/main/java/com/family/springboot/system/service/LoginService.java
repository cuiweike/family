package com.family.springboot.system.service;

public interface LoginService {

    /**
     * 通过用户名和密码登录
     * 2019/02/16
     *
     * @param userName  用户名
     * @param passWord  密码
     * @return
     */
    String login(String userName, String passWord);
}
