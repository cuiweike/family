package com.family.springboot.money.service.impl;

import com.family.springboot.money.service.EnumFundsService;
import com.family.utils.MenuTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EnumFundsServiceImpl implements EnumFundsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getData() {
        StringBuilder sb = new StringBuilder("select id  ,name as text,parentId as pid, type from b_enum_funds ");

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString());
        MenuTreeUtil menuTreeUtil = new MenuTreeUtil();

        return menuTreeUtil.menuList(list);
    }

    @Override
    public boolean saveEnum(String id, String parentId, String name) {
        StringBuilder sb = new StringBuilder("insert into b_enum_funds (id,type,name,parentId)values(");
        sb.append("'").append(id).append("',");
        sb.append("(select type from b_enum_funds where id='").append(parentId).append("'),");
        sb.append("'").append(name).append("',");
        sb.append("'").append(parentId).append("')");
        jdbcTemplate.execute(sb.toString());
        return true;
    }
}
