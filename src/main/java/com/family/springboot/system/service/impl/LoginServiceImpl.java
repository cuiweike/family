package com.family.springboot.system.service.impl;

import com.family.springboot.system.service.LoginService;
import com.family.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String login(String userName, String passWord) {

        StringBuilder sql1 = new StringBuilder("select count(*) from p_user where userName='").append(userName).append("'");
        Integer count = jdbcTemplate.queryForObject(sql1.toString(), Integer.class);
        if (count == 0) {
            return "用户名错误";
        }
        String pwd = CommonUtils.getMD5(passWord);
        StringBuilder sql2 = new StringBuilder("select count(*) from p_user where userName='").append(userName).append("'");
        sql2.append(" and password='").append(pwd).append("'");

        Integer count2 = jdbcTemplate.queryForObject(sql2.toString(), Integer.class);
        if (count2 == 0) {
            return "密码错误";
        }
        return "";
    }
}
