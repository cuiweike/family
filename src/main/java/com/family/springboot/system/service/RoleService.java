package com.family.springboot.system.service;

import com.family.springboot.system.dto.RoleDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RoleService {

    /**
     * 查询
     * 2019/02/11
     *
     * @param roleName        角色名
     * @param createTimeStart 创建时间
     * @param createTimeEnd   至
     * @return
     */
    String getData(String roleName, String createTimeStart, String createTimeEnd);

    /**
     * 记录数
     * 2019/02/11
     *
     * @param sql
     * @return
     */
    Integer getCount(String sql);

    /**
     * 分页sql
     * 2019/02/11
     *
     * @param sql
     * @param rn
     * @param rowNumber
     * @return
     */
    List<RoleDto> getRows(String sql, int rn, int rowNumber);

    /**
     * 验证角色名重复
     * 2019/02/11
     *
     * @param roleId   角色id
     * @param roleName 角色名
     * @return
     */
    boolean verifyRole(String roleId, String roleName);

    /**
     * 新增和修改
     * 2019/02/11
     *
     * @param roleId   角色id
     * @param roleName 角色名
     * @param desc     描述
     * @param request
     * @return
     */
    boolean insertOrUpdateRole(String roleId, String roleName, String desc, HttpServletRequest request);

    /**
     * 批量删除角色
     * 2019/02/11
     *
     * @param roleIdArr 角色id数组
     * @param request
     * @return
     */
    boolean deleteRole(String roleIdArr, HttpServletRequest request);

    /**
     * 为当前角色保存权限
     * 2019/02/16
     *
     * @param roleId            角色id
     * @param permissionIdArr   权限id数组
     * @param request
     * @return
     */
    boolean savePermission(String roleId, String permissionIdArr, HttpServletRequest request);
}
