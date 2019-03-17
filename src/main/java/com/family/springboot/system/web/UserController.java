package com.family.springboot.system.web;

import com.alibaba.fastjson.JSON;
import com.family.springboot.system.dto.UserDto;
import com.family.springboot.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userController")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getData")
    public String getData(String userName, String roleId, String sex, String createTimeStart, String createTimeEnd, HttpServletRequest request) {
        int rn = Integer.parseInt(request.getParameter("page"));
        int rowNumber = Integer.parseInt(request.getParameter("rows"));
        String sql = userService.getSql(userName, roleId, sex, createTimeStart, createTimeEnd);

        Integer total = userService.getTotal(sql);
        List<UserDto> rows = userService.getRows(sql, rn, rowNumber);
        List<UserDto> rows2 = userService.getRows2(userName,roleId,sex,createTimeStart,createTimeEnd, rn, rowNumber);
        List<Map<String,Object>> row3=userService.getRows3(userName,roleId,sex,createTimeStart,createTimeEnd, rn, rowNumber);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", rows2);
        return JSON.toJSONString(map);
    }

    @RequestMapping("/deleteUser")
    public boolean deleteUser(String userIdArr,HttpServletRequest request) {
        boolean flag = false;
        try {
            flag = userService.deleteUser(userIdArr,request);
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
        return flag;
    }

    @RequestMapping("/getSex")
    public String getSex() {
        List<Map<String, Object>> list = null;
        try {
            list = userService.getSex();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(list);
    }

    @RequestMapping("/getRole")
    public String getRole() {
        List<Map<String, Object>> list = null;
        try {
            list = userService.getRole();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(list);
    }

    @RequestMapping("/verifyUser")
    public boolean verifyUser(String userId, String userName) {
        boolean flag = false;
        try {
            flag = userService.verifyUser(userId, userName);
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
        return flag;
    }

    @RequestMapping("/insertOrUpdateUser")
    public boolean insertOrUpdateUser(String userId, String userName, String password, String sex, String roleId, String brithday,HttpServletRequest request) {
        boolean flag = false;
        try {
            flag = userService.insertOrUpdateUser(userId, userName, password, sex, roleId, brithday,request);
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
        return flag;
    }
}
