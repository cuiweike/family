package com.family.springboot;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class HtmlController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @RequestMapping("/login")
//    public String login() {
//        return "login";
//    }

//    @RequestMapping("/main")
//    public String main() {
//        return "main";
//    }
//
//    @RequestMapping("/user")
//    public String user() {
//        return "system/user";
//    }
//
//    @RequestMapping("/role")
//    public String role() {
//        return "system/role";
//    }
//
//    @RequestMapping("/money/enum")
//    public String moneyEnum() {
//        return "money/enumMaintain";
//    }

    @RequestMapping(value = "/u")
    @ResponseBody
    public String u(HttpServletRequest request) {
        String sql = "select * from p_user";
        int rn = Integer.parseInt(request.getParameter("page"));
        int rowNumber = Integer.parseInt(request.getParameter("rows"));
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return JSON.toJSONString(list);
    }
}
