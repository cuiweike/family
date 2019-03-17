package com.family.springboot.system.service.impl;

import com.family.springboot.system.dto.OperLogDto;
import com.family.springboot.system.service.OperLogService;
import com.family.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class OperServiceImpl implements OperLogService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertOperLog(HttpServletRequest request, String button) {
        Cookie[] cookie = request.getCookies();
        String userId = "";
        for (Cookie c : cookie) {
            switch (c.getName()) {
                case "userId":
                    userId = c.getValue();
                    break;
                default:
                    break;
            }
        }
        String primaryKey= UUID.randomUUID().toString().replace("-","");
        StringBuilder sb=new StringBuilder("insert into P_LOG_OPER (ID,USERID,\"VIEW\",BUTTON,OPERTIME)values(");
        sb.append("'").append(primaryKey).append("',");
        sb.append("'").append(userId).append("',");
        sb.append("(SELECT PARENTID FROM P_PERMISSION WHERE MARK='").append(button).append("'),");
        sb.append("(SELECT PERMISSIONID FROM P_PERMISSION WHERE MARK='").append(button).append("'),");
        sb.append("to_date('").append(CommonUtils.getCurrentTime()).append("','yyyy-mm-dd hh24:mi:ss'))");

        jdbcTemplate.execute(sb.toString());
    }

    @Override
    public String getSql(String userName, String view, String button, String operTimeStart, String operTimeEnd) {
        StringBuilder sb=new StringBuilder(" SELECT t0.ID,t0.OPERTIME,t1.USERNAME,t2.permissionNAME as VIEWNAME,T3.permissionNAME AS BUTTONNAME FROM P_LOG_OPER t0 ");
        sb.append(" LEFT JOIN P_USER t1 on t0.USERID=t1.USERID ");
        sb.append(" LEFT JOIN P_PERMISSION t2 on t0.\"VIEW\"=t2.permissionID ");
        sb.append(" LEFT JOIN P_PERMISSION t3 on t0.button=t3.permissionID ");
        sb.append(" where 1=1 ");
        if(!"".equals(userName)&&null!=userName){
            sb.append(" and t1.username like '%").append(userName).append("%'");
        }
        if(!"".equals(view)&&null!=view){
            sb.append(" and t0.\"VIEW\"='").append(view).append("'");
        }
        if(!"".equals(button)&&null!=button){
            sb.append(" and t0.button='").append(button).append("'");
        }
        if(!"".equals(operTimeStart)&&null!=operTimeStart){
            sb.append(" and t0.opertime>=to_date('").append(operTimeStart).append("','yyyy-mm-dd')");
        }
        if(!"".equals(operTimeEnd)&&null!=operTimeEnd){
            sb.append(" and t0.opertime<=to_date('").append(operTimeEnd).append(" 23:59:59', 'yyyy-mm-dd hh24:mi:ss')");
        }
        sb.append(" order by t0.opertime desc");
        return sb.toString();
    }

    @Override
    public Integer getCount(String sql) {
        Integer count=null;
        try {
            count=jdbcTemplate.queryForObject(CommonUtils.setCount(sql),Integer.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<OperLogDto> getRows(String sql, int rn, int rowNumber) {
        final List<OperLogDto> list=new ArrayList<>();
        try {
            jdbcTemplate.query(CommonUtils.setPage(sql, rn, rowNumber), new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    OperLogDto operLogDto=new OperLogDto();
                    operLogDto.setId(resultSet.getString("ID"));
                    operLogDto.setUserName(resultSet.getString("USERNAME"));
                    operLogDto.setViewName(resultSet.getString("VIEWNAME"));
                    operLogDto.setButtonName(resultSet.getString("BUTTONNAME"));
                    operLogDto.setOperTime(resultSet.getString("OPERTIME"));
                    list.add(operLogDto);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getModule() {
        StringBuilder sb=new StringBuilder("select PERMISSIONID,PERMISSIONNAME from P_PERMISSION where type='VIEW'");
        final List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("id","");
        map.put("name","-请选择-");
        list.add(map);
        jdbcTemplate.query(sb.toString(), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Map<String,Object> map=new HashMap<>();
                map.put("id",resultSet.getString("PERMISSIONID"));
                map.put("name",resultSet.getString("PERMISSIONNAME"));
                list.add(map);
            }
        });
        return list;
    }

    @Override
    public List<Map<String, Object>> getButton(String module) {
        StringBuilder sb=new StringBuilder("select PERMISSIONID,PERMISSIONNAME from P_PERMISSION where type='BUTTON' AND PARENTID='").append(module).append("'");
        final List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("id","");
        map.put("name","-请选择-");
        list.add(map);
        jdbcTemplate.query(sb.toString(), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Map<String,Object> map=new HashMap<>();
                map.put("id",resultSet.getString("PERMISSIONID"));
                map.put("name",resultSet.getString("PERMISSIONNAME"));
                list.add(map);
            }
        });
        return list;
    }
}
