package com.family.springboot.system.service.impl;

import com.family.springboot.system.dto.RoleDto;
import com.family.springboot.system.service.OperLogService;
import com.family.springboot.system.service.RoleService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private OperLogService operLogService;

    @Override
    public String getData(String roleName, String createTimeStart, String createTimeEnd) {
        StringBuilder sb = new StringBuilder("SELECT ROLEID,ROLENAME,\"DESC\" from P_ROLE where state='1' ");
        if (!"".equals(roleName) && null != roleName) {
            sb.append(" and ROLENAME like '%").append(roleName).append("%'");
        }
        if (!"".equals(createTimeStart) && null != createTimeStart) {
            sb.append(" and createTime >= TO_DATE('").append(createTimeStart).append("','yyyy-mm-dd') ");
        }
        if (!"".equals(createTimeEnd) && null != createTimeEnd) {
            sb.append(" and createTime <= TO_DATE('").append(createTimeEnd).append(" 23:59:59', 'yyyy-mm-dd hh24:mi:ss')");
        }
        return sb.toString();
    }

    @Override
    public Integer getCount(String sql) {
        return jdbcTemplate.queryForObject(CommonUtils.setCount(sql), Integer.class);
    }

    @Override
    public List<RoleDto> getRows(String sql, int rn, int rowNumber) {
        final List<RoleDto> list = new ArrayList<>();
        jdbcTemplate.query(CommonUtils.setPage(sql, rn, rowNumber), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                RoleDto roleDto = new RoleDto();
                roleDto.setRoleId(resultSet.getString("ROLEID"));
                roleDto.setRoleName(resultSet.getString("ROLENAME"));
                roleDto.setDesc(resultSet.getString("DESC"));
                list.add(roleDto);
            }
        });
        return list;
    }

    @Override
    public boolean verifyRole(String roleId, String roleName) {
        StringBuilder sb = new StringBuilder("select count(*) from p_role where state='1' and rolename='").append(roleName).append("'");
        //为空为null--新增,不为空为修改
        if (null != roleId && !"".equals(roleId)) {
            sb.append(" and roleId <> '").append(roleId).append("'");
        }
        Integer count = jdbcTemplate.queryForObject(sb.toString(), Integer.class);
        //重复
        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean insertOrUpdateRole(String roleId, String roleName, String desc, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder("");
        //roleId为空--新增,不为空--修改
        if ("".equals(roleId) || null == roleId) {
            String primaryKey = UUID.randomUUID().toString().replace("-", "");
            sb.append("insert into p_role (roleId,roleName,state,\"DESC\",createTime)values(");
            sb.append("'").append(primaryKey).append("',");
            sb.append("'").append(roleName).append("',");
            sb.append("'").append(1).append("',");
            sb.append("'").append(desc).append("',");
            sb.append("TO_DATE('").append(CommonUtils.getCurrentTime()).append("','yyyy-mm-dd hh24:mi:ss'))");
            jdbcTemplate.execute(sb.toString());
            operLogService.insertOperLog(request, EnumPermission.ROLE_ADD);
        } else {
            sb.append("update p_role set ");
            sb.append("roleName='").append(roleName).append("',");
            sb.append("\"DESC\"='").append(desc).append("'");
            sb.append(" where roleId='").append(roleId).append("'");
            jdbcTemplate.execute(sb.toString());
            operLogService.insertOperLog(request,EnumPermission.ROLE_UPDATE);
        }
        return true;
    }

    @Transactional
    @Override
    public boolean deleteRole(String roleIdArr, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder("update p_role set state='0', ");
        sb.append(" DELETETIME=").append("TO_DATE('").append(CommonUtils.getCurrentTime()).append("','yyyy-mm-dd hh24:mi:ss ') ");
        sb.append(" where roleId in(").append(roleIdArr).append(")");
        jdbcTemplate.execute(sb.toString());
        operLogService.insertOperLog(request,EnumPermission.ROLE_DELETE);
        return true;
    }

    @Transactional
    @Override
    public boolean savePermission(String roleId, String permissionIdArr, HttpServletRequest request) {
        //先删后增
        StringBuilder sb=new StringBuilder("delete from m_role_permission where roleId='").append(roleId).append("'");
        jdbcTemplate.execute(sb.toString());

        String[] idArr=permissionIdArr.split(",");
        for(int i=0;i<idArr.length;i++){
            StringBuilder insertSb=new StringBuilder(" insert into m_role_permission (roleId,permissionId)values(");
            insertSb.append("'").append(roleId).append("',");
            insertSb.append(idArr[i]).append(")");
            jdbcTemplate.execute(insertSb.toString());
        }
        operLogService.insertOperLog(request,EnumPermission.ROLE_ADJUST);
        return true;
    }

}
