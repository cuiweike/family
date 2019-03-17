package com.family.springboot.money.web;

import com.alibaba.fastjson.JSON;
import com.family.springboot.money.service.EnumFundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/enumFundsController")
public class EnumFundsController {

    @Autowired
    private EnumFundsService enumFundsService;

    @RequestMapping("/getData")
    public String getData(HttpServletRequest request) {
        List<Map<String, Object>> list = null;
        try {
            list = enumFundsService.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(list);
    }

    @RequestMapping("/saveEnum")
    public boolean saveEnum(String id, String parentId, String name) {
        boolean flag = false;
        try {
            flag = enumFundsService.saveEnum(id, parentId, name);
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
        return flag;
    }
}
