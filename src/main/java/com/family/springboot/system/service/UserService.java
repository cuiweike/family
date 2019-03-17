package com.family.springboot.system.service;

import com.family.springboot.system.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 查询sql
     * 2019/2/11
     *
     * @param userName        用户名
     * @param roleId          角色id
     * @param sex             性别
     * @param createTimeStart 创建时间
     * @param createTimeEnd   至
     * @return
     */
    String getSql(String userName, String roleId, String sex, String createTimeStart, String createTimeEnd);

    /**
     * 记录数
     * 2019/02/11
     *
     * @param sql
     * @return
     */
    Integer getTotal(String sql);

    /**
     * 分页
     * 2019/02/11
     *
     * @param sql
     * @param rn
     * @param rowNumber
     * @return
     */
    List<UserDto> getRows(String sql, int rn, int rowNumber);

    /**
     * 删除用户
     * 2019/02/11
     *
     * @param userIdArr
     * @param request
     * @return
     */
    boolean deleteUser(String userIdArr, HttpServletRequest request);

    /**
     * 性别下拉框
     * 2019/02/11
     *
     * @return
     */
    List<Map<String, Object>> getSex();

    /**
     * 角色下拉框
     * 2019/02/11
     *
     * @return
     */
    List<Map<String, Object>> getRole();

    /**
     * 验证用户名是否重复
     * 2019/02/11
     *
     * @param userId   用户id
     * @param userName 用户名
     * @return
     */
    boolean verifyUser(String userId, String userName);

    /**
     * 新增和修改
     * 2019/02/11
     *
     * @param userId   用户id
     * @param userName 用户名
     * @param password 密码
     * @param sex      性别
     * @param roleId   角色id
     * @param brithday 生日
     * @param request
     * @return
     */
    boolean insertOrUpdateUser(String userId, String userName, String password, String sex, String roleId, String brithday, HttpServletRequest request);

    /**
     * 获取user对象
     * 2019/02/16
     *
     * @param userName
     * @return
     */
    UserDto getUser(String userName);



    /*******************************************************Mybatis方法**************************************************************/

    /**
     *
     * @param userName
     * @param roleId
     *@param sex
     * @param createTimeStart
     * @param createTimeEnd
     * @param rn
     * @param rowNumber   @return
     */
    List<UserDto> getRows2(String userName, String roleId, String sex, String createTimeStart, String createTimeEnd, int rn, int rowNumber);

    List<Map<String,Object>> getRows3(String userName, String roleId, String sex, String createTimeStart, String createTimeEnd, int rn, int rowNumber);
}
