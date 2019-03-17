package com.family.springboot.system.web;

import com.alibaba.fastjson.JSON;
import com.family.springboot.system.dto.RoleDto;
import com.family.springboot.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roleController")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/getData")
    public String getData(String roleName, String createTimeStart, String createTimeEnd, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            String sql = roleService.getData(roleName, createTimeStart, createTimeEnd);

            Integer total = roleService.getCount(sql);

            int rn = Integer.parseInt(request.getParameter("page"));
            int rowNumber = Integer.parseInt(request.getParameter("rows"));

            List<RoleDto> list = roleService.getRows(sql, rn, rowNumber);

            map.put("total", total);
            map.put("rows", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/verifyRole")
    public boolean verifyRole(String roleId, String roleName) {
        boolean flag = false;
        try {
            flag = roleService.verifyRole(roleId, roleName);
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
        return flag;
    }

    @RequestMapping("/insertOrUpdateRole")
    public boolean insertOrUpdateRole(String roleId, String roleName, String desc,HttpServletRequest request) {
        boolean flag = false;
        try {
            flag = roleService.insertOrUpdateRole(roleId, roleName, desc,request);
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
        return flag;
    }

    @RequestMapping("/deleteRole")
    public boolean deleteRole(String roleIdArr,HttpServletRequest request) {
        boolean flag = false;
        try {
            flag = roleService.deleteRole(roleIdArr,request);
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
        return flag;
    }

    @RequestMapping("/savePermission")
    public boolean savePermission(String roleId,String permissionIdArr,HttpServletRequest request){
        boolean flag=false;
        try {
            flag=roleService.savePermission(roleId,permissionIdArr,request);
        }catch (Exception e){
            e.printStackTrace();
            return flag;
        }
        return flag;
    }
}
