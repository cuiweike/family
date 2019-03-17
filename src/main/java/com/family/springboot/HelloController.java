package com.family.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class HelloController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/hello")
    public String say() {
        for (int i = 0; i < 10; i++) {
            System.out.println(UUID.randomUUID().toString().replace("-", ""));
        }

        return "hello";
    }

    @RequestMapping("/zeroException")
    public int zeroException() {
        return 100 / 0;
    }

    @RequestMapping("/database")
    public void test() {
        String sql = "select * from p_user";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        System.out.println(list);
    }
}
