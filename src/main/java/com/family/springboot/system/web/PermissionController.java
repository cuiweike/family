package com.family.springboot.system.web;

import com.alibaba.fastjson.JSON;
import com.family.springboot.system.dto.PermissionDto;
import com.family.springboot.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/permissionController")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/permissionTree")
    public String permissionTree(String roleId) {
        List<Map<String, Object>> list = null;
        try {
            list = permissionService.permissionTree(roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(list);
    }

    @RequestMapping("/moduleTree")
    public String moduleTree(String userId) {
        List<Map<String, Object>> list = null;
        try {
            list = permissionService.getModuleTree(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(list);
    }

    @RequestMapping("/getPermission")
    public String getPermission(String userId,String permissionId){
        List<PermissionDto> list=null;
        try {
            list=permissionService.getPermission(userId,permissionId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(list);
    }
}
