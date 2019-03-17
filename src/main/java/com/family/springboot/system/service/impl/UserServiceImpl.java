package com.family.springboot.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.family.springboot.system.dao.UserDao;
import com.family.springboot.system.dao.UserRoleDao;
import com.family.springboot.system.dto.UserDto;
import com.family.springboot.system.entity.User;
import com.family.springboot.system.entity.UserRole;
import com.family.springboot.system.service.OperLogService;
import com.family.springboot.system.service.UserService;
import com.family.utils.CommonUtils;
import com.family.utils.EnumPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author zjc
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private OperLogService operLogService;
    @Autowired
    private UserDao userDao;

    @Override
    public String getSql(String userName, String roleId, String sex, String createTimeStart, String createTimeEnd) {
        StringBuilder sb = new StringBuilder(" SELECT t0.USERID,t0.USERNAME,t0.PASSWORD,t0.SEX,t0.BRITHDAY,t2.ROLEID,t2.ROLENAME,t3.name as SEXNAME FROM P_USER t0 ");
        sb.append(" LEFT JOIN M_USER_ROLE t1 ON t0.USERID=t1.USERID ");
        sb.append(" LEFT JOIN P_ROLE t2 ON t1.roleid=t2.roleid ");
        sb.append(" LEFT JOIN B_COUNT t3 ON t0.sex=t3.id ");
        sb.append(" where t0.state<>0 and t3.TYPE='SEX'");
        if (null != userName && !"".equals(userName)) {
            sb.append(" and t0.username like '%").append(userName).append("%' ");
        }
        if (null != roleId && !"".equals(roleId)) {
            sb.append(" and t2.roleid='").append(roleId).append("' ");
        }
        if (null != sex && !"".equals(sex)) {
            sb.append(" and t0.sex='").append(sex).append("' ");
        }
        if (null != createTimeStart && !"".equals(createTimeStart)) {
            sb.append(" and t0.createTime >= TO_DATE('").append(createTimeStart).append("','yyyy-mm-dd') ");
        }
        if (null != createTimeEnd && !"".equals(createTimeEnd)) {
            sb.append(" and t0.createTime <= TO_DATE('").append(createTimeEnd).append(" 23:59:59', 'yyyy-mm-dd hh24:mi:ss')");
        }
        return sb.toString();
    }

    @Override
    public Integer getTotal(String sql) {
//        return jdbcTemplate.queryForObject(CommonUtils.setCount(sql), Integer.class);
        return userDao.getTotal();
    }


    @Override
    public List<UserDto> getRows(String sql, int rn, int rowNumber) {
        final List<UserDto> rows = new ArrayList<>();
        jdbcTemplate.query(CommonUtils.setPage(sql, rn, rowNumber), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                UserDto userDto = new UserDto();
                userDto.setUserId(resultSet.getString("USERID"));
                userDto.setUserName(resultSet.getString("USERNAME"));
                userDto.setRoleName(resultSet.getString("ROLENAME"));
                userDto.setSex(resultSet.getString("SEX"));
                userDto.setRoleId(resultSet.getString("ROLEID"));
                userDto.setBrithday(resultSet.getString("BRITHDAY"));
                userDto.setPassWord(resultSet.getString("PASSWORD"));
                userDto.setSexName(resultSet.getString("SEXNAME"));
                rows.add(userDto);
            }
        });
        return rows;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUser(String userIdArr, HttpServletRequest request) {
//        StringBuilder sb = new StringBuilder(" UPDATE P_USER SET STATE=0 ,");
//        sb.append(" DELETETIME=").append("TO_DATE('").append(CommonUtils.getCurrentTime()).append("','yyyy-mm-dd hh24:mi:ss ') ");
//        sb.append(" WHERE USERID in(").append(userIdArr).append(")");
//        jdbcTemplate.execute(sb.toString());
        userDao.deleteUser(userIdArr);
        operLogService.insertOperLog(request,EnumPermission.USER_DELETE);
        return true;
    }

    @Override
    public List<Map<String, Object>> getSex() {
//        StringBuilder sb = new StringBuilder("SELECT ID,NAME FROM B_COUNT WHERE TYPE='SEX'");
        final List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", "");
        map.put("name", "-请选择-");
        list.add(map);
        List<Map<String, Object>> list2=userDao.getSex();
        for (int i=0;i<list2.size();i++){
            list.add(list2.get(i));
        }
//        jdbcTemplate.query(sb.toString(), new RowCallbackHandler() {
//            @Override
//            public void processRow(ResultSet resultSet) throws SQLException {
//                Map<String, Object> map = new HashMap<>();
//                map.put("id", resultSet.getString("ID"));
//                map.put("name", resultSet.getString("NAME"));
//                list.add(map);
//            }
//        });
        return list;
    }

    @Override
    public List<Map<String, Object>> getRole() {
//        StringBuilder sb = new StringBuilder("SELECT ROLEID,ROLENAME FROM P_ROLE WHERE STATE='1'");
        final List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", "");
        map.put("name", "-请选择-");
        list.add(map);
        List<Map<String, Object>> list2=userDao.getRole();
        for(int i=0;i<list2.size();i++){
            list.add(list2.get(i));
        }
//        jdbcTemplate.query(sb.toString(), new RowCallbackHandler() {
//            @Override
//            public void processRow(ResultSet resultSet) throws SQLException {
//                Map<String, Object> map = new HashMap<>();
//                map.put("id", resultSet.getString("ROLEID"));
//                map.put("name", resultSet.getString("ROLENAME"));
//                list.add(map);
//            }
//        });
        return list;
    }

    @Override
    public boolean verifyUser(String userId, String userName) {
//        StringBuilder sb = new StringBuilder("select count(*) from p_user where state='1' and username='").append(userName).append("'");
//        //为空为null--新增,不为空为修改
//        if (null != userId && !"".equals(userId)) {
//            sb.append(" and userId <> '").append(userId).append("'");
//        }
//        Integer count = jdbcTemplate.queryForObject(sb.toString(), Integer.class);
        Integer count=userDao.verifyUser(userId,userName);
        //重复
        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertOrUpdateUser(String userId, String userName, String password, String sex, String roleId, String brithday, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder("");
        StringBuilder sbMid = new StringBuilder("");
        String pwd = "";
        if (!"".equals(password) && null != password) {
                pwd=CommonUtils.getMD5(pwd);
            }
        //
        if ("".equals(userId) || null == userId) {
            String primaryId = UUID.randomUUID().toString().replace("-", "");
            //主表insert
            userDao.insertUser(primaryId,userName,pwd,sex,roleId,brithday);
//            sb.append("insert into p_user (userId,userName,password,state,sex,brithday,createTime)values(");
//            sb.append("'").append(primaryId).append("',");
//            sb.append("'").append(userName).append("',");
//            sb.append("'").append(pwd).append("',");
//            sb.append("'").append(1).append("',");
//            sb.append("'").append(sex).append("',");
//            sb.append("TO_DATE('").append(brithday).append("','yyyy-mm-dd'),");
//            sb.append("TO_DATE('").append(CommonUtils.getCurrentTime()).append("','yyyy-mm-dd hh24:mi:ss'))");
//            jdbcTemplate.execute(sb.toString());
            //中间表insert
            userDao.insertUserRole(primaryId,roleId);
//            sbMid.append("insert into m_user_role (userId,roleId) values(");
//            sbMid.append("'").append(primaryId).append("',");
//            sbMid.append("'").append(roleId).append("')");
//            jdbcTemplate.execute(sbMid.toString());
            operLogService.insertOperLog(request,EnumPermission.USER_ADD);
        } else {
            //主表修改

            userDao.updateUser(userId,userName,pwd,sex,brithday);
//            sb.append("update p_user set ");
//            sb.append("username='").append(userName).append("',");
//            if (!"".equals(password) && null != password) {
//                sb.append("password='").append(pwd).append("',");
//            }
//            sb.append("sex='").append(sex).append("',");
//            sb.append("brithday= TO_DATE('").append(brithday).append("','yyyy-mm-dd')");
//            sb.append(" where userid ='").append(userId).append("'");
//            jdbcTemplate.execute(sb.toString());
            //中间表修改
            userDao.updateUserRole(userId,roleId);
//            sbMid.append("update m_user_role set roleId='").append(roleId).append("'");
//            sbMid.append(" where userId='").append(userId).append("'");
//            jdbcTemplate.execute(sbMid.toString());
            operLogService.insertOperLog(request, EnumPermission.USER_UPDATE);
        }
        return true;
    }

    @Override
    public UserDto getUser(String userName) {
        StringBuilder sb = new StringBuilder("select userId,userName from p_user where userName='").append(userName).append("'");
        final UserDto userDto = new UserDto();
        jdbcTemplate.query(sb.toString(), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                userDto.setUserId(resultSet.getString("USERID"));
                userDto.setUserName(resultSet.getString("USERNAME"));
            }
        });
        return userDto;
    }

    @Override
    public List<UserDto> getRows2(String userName, String roleId, String sex, String createTimeStart, String createTimeEnd, int rn, int rowNumber) {
        List<UserDto> list=userDao.findUser(userName,roleId,sex,createTimeStart,createTimeEnd,rn,rowNumber);

        return list;
    }

    @Override
    public List<Map<String, Object>> getRows3(String userName, String roleId, String sex, String createTimeStart, String createTimeEnd, int rn, int rowNumber) {
        List<Map<String,Object>> list1=new ArrayList<>();
        list1=userDao.findUser2(userName,roleId,sex,createTimeStart,createTimeEnd,rn,rowNumber);
        return list1;
    }
}
