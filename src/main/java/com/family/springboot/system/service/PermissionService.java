package com.family.springboot.system.service;

import com.family.springboot.system.dto.PermissionDto;

import java.util.List;
import java.util.Map;

public interface PermissionService {
    /**
     * 获取权限树
     *
     * @param roleId
     * @return
     */
    List<Map<String,Object>> permissionTree(String roleId);


    /**
     * 获取模块菜单树
     *
     * @param userId
     * @return
     */
    List<Map<String,Object>> getModuleTree(String userId);

    /**
     * 通过userId获取权限树
     * 2019/02/17
     *
     * @param userId    用户id
     * @param permissionId
     * @return
     */
    List<PermissionDto> getPermission(String userId, String permissionId);
}
