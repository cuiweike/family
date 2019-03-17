package com.family.springboot.system.service.impl;

import com.family.springboot.system.dto.PermissionDto;
import com.family.springboot.system.service.PermissionService;
import com.family.utils.MenuTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> permissionTree(String roleId) {
        MenuTreeUtil menuTreeUtil = new MenuTreeUtil();
        StringBuilder sb = new StringBuilder("SELECT PERMISSIONID as id,PERMISSIONNAME as text,PARENTID as pid FROM P_PERMISSION");

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString());
        StringBuilder mSb = new StringBuilder("select * from m_role_permission where roleId='").append(roleId).append("'");
        List<Map<String, Object>> mList = jdbcTemplate.queryForList(mSb.toString());
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < mList.size(); j++) {
                if (list.get(i).get("ID").toString().equals(mList.get(j).get("PERMISSIONID"))) {
                    list.get(i).put("checked", true);
                }
            }
        }


        //整理成树结构
        return menuTreeUtil.menuList(list);
    }

    @Override
    public List<Map<String, Object>> getModuleTree(String userId) {
        StringBuilder sb = new StringBuilder(" SELECT t4.permissionId as id,t4.permissionName as text,t4.parentId as pid,t4.path  from P_USER t0 ");
        sb.append(" LEFT JOIN M_USER_ROLE t1 ON t0.USERID=t1.USERID ");
        sb.append(" LEFT JOIN P_ROLE t2 ON t1.roleId=t2.roleId  ");
        sb.append(" LEFT JOIN M_ROLE_PERMISSION t3 ON t2.roleId=t3.roleId ");
        sb.append(" LEFT JOIN P_PERMISSION t4 ON t3.permissionId=t4.permissionId ");
        sb.append(" WHERE t4.type<>'BUTTON' ");
        sb.append(" AND t0.USERID='").append(userId).append("'");
        sb.append(" order by t4.SORT");

        List<Map<String, Object>> list = new ArrayList<>();
        list = jdbcTemplate.queryForList(sb.toString());

        return menuList(list);
    }

    @Override
    public List<PermissionDto> getPermission(String userId, String permissionId) {
        StringBuilder sb=new StringBuilder("select permissionName from p_permission where parentId='").append(permissionId).append("'");
        sb.append(" and permissionId in (").append(" SELECT t2.permissionId from P_USER t0 ");
        sb.append(" INNER JOIN M_USER_ROLE t1 on t0.USERID=t1.USERID ");
        sb.append(" INNER JOIN M_ROLE_PERMISSION t2 on t1.roleId=t2.roleId ");
        sb.append(" WHERE t0.USERID='").append(userId).append("')");
        final List<PermissionDto> list=new ArrayList<>();
        jdbcTemplate.query(sb.toString(), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                PermissionDto permissionDto=new PermissionDto();
                permissionDto.setPermissionName(resultSet.getString("PERMISSIONNAME"));
                list.add(permissionDto);
            }
        });

        return list;
    }

    public List<Map<String, Object>> menuList(List<Map<String, Object>> menu) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Map<String, Object> x : menu) {
            Map<String, Object> mapArr = new LinkedHashMap<String, Object>();
            if (null == x.get("PID")) {
                mapArr.put("id", x.get("ID").toString());
                mapArr.put("text", x.get("TEXT").toString());
                mapArr.put("pid", x.get("PID"));
                mapArr.put("path", x.get("PATH"));
                //父节点展开
                mapArr.put("state", "closed");
                //mapArr.put("expanded", true);
                mapArr.put("children", menuChild(menu, x.get("ID").toString()));
                list.add(mapArr);
            }
        }
        return list;
    }

    public List<Map<String, Object>> menuChild(List<Map<String, Object>> menu, String id) {
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> childrenLists = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> a : menu) {
            Map<String, Object> childArray = new LinkedHashMap<String, Object>();
            //有父节点存在
            if (id.equals(a.get("PID"))) {
                childArray.put("id", a.get("ID").toString());
                childArray.put("text", a.get("TEXT").toString());
                childArray.put("pid", a.get("PID").toString());
                childrenLists = menuChild(menu, a.get("ID").toString());
                childArray.put("children", childrenLists);
                childArray.put("path", a.get("PATH"));
                //最后一层子节点状态更改
                if (childrenLists.size() == 0) {
//                    childArray.put("state", "open");
                    childArray.remove("children");
                    //最后一层被选中时标记
                    if (null != a.get("checked")) {
                        childArray.put("checked", true);
                    }
                } else {
                    childArray.put("state", "closed");
                }
                lists.add(childArray);
            }
        }
        return lists;
    }
}
