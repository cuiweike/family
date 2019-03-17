package com.family.springboot.system.dao;

import com.family.common.pojo.Count;
import com.family.springboot.system.dto.UserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zjc
 */
@Repository("UserDao")
public interface UserDao {
    /**
     * 创建视图类
     *
     * @param userName
     * @param roleId
     * @param sex
     * @param createTimeStart
     * @param createTimeEnd
     * @param page
     * @param rows
     * @return
     */
    List<UserDto> findUser(@Param("userName") String userName,
                           @Param("roleId") String roleId,
                           @Param("sex") String sex,
                           @Param("createTimeStart") String createTimeStart,
                           @Param("createTimeEnd") String createTimeEnd,
                           @Param("page") Integer page,
                           @Param("rows") Integer rows);

    /**
     * 返回值Map
     *
     * @param userName
     * @param roleId
     * @param sex
     * @param createTimeStart
     * @param createTimeEnd
     * @param page
     * @param rows
     * @return
     */
    List<Map<String, Object>> findUser2(@Param("userName") String userName,
                                        @Param("roleId") String roleId,
                                        @Param("sex") String sex,
                                        @Param("createTimeStart") String createTimeStart,
                                        @Param("createTimeEnd") String createTimeEnd,
                                        @Param("page") Integer page,
                                        @Param("rows") Integer rows);

    List<Count> getCount();

    /**
     * 获取记录数
     *
     * @return
     */
    Integer getTotal();

    /**
     * 逻辑删除
     *
     * @param userIdArr
     */
    void deleteUser(@Param("userIdArr") String userIdArr);

    /**
     * 获取性别下拉框
     *
     * @return
     */
    List<Map<String, Object>> getSex();

    /**
     * 获取角色下拉框
     *
     * @return
     */
    List<Map<String, Object>> getRole();

    /**
     * 新增和修改时验证用户名重复
     *
     * @param userId
     * @param userName
     * @return
     */
    Integer verifyUser(@Param("userId") String userId, @Param("userName") String userName);

    /**
     * zjc
     * 2019/03/16
     * 新增
     *
     * @param userName
     * @param password
     * @param sex
     * @param roleId
     * @param brithday
     */
    void insertUser(@Param("userId") String userId,@Param("userName") String userName, @Param("password") String password,
                    @Param("sex") String sex, @Param("roleId") String roleId, @Param("brithday") String brithday);

    /**
     * zjc
     * 2091/03/16
     * 用户角色中间表新增
     * @param userId
     * @param roleId
     */
    void insertUserRole(@Param("userId") String userId,@Param("roleId") String roleId);

    /**
     * 修改用户
     * @param userId
     * @param userName
     * @param pwd
     * @param sex
     * @param brithday
     */
    void updateUser(@Param("userId") String userId,@Param("userName") String userName,@Param("pwd") String pwd,
                    @Param("sex") String sex,@Param("brithday") String brithday);

    /**
     * zjc
     * 2019/03/14
     * 修改用户角色中间表
     * @param userId
     * @param roleId
     */
    void updateUserRole(@Param("userId")String userId, @Param("roleId")String roleId);
}
