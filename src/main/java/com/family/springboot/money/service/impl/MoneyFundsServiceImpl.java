package com.family.springboot.money.service.impl;

import com.family.springboot.money.dto.MoneyFundsDto;
import com.family.springboot.money.service.MoneyFundsService;
import com.family.springboot.system.service.OperLogService;
import com.family.utils.CommonUtils;
import com.family.utils.EnumPermission;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class MoneyFundsServiceImpl implements MoneyFundsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private OperLogService operLogService;

    @Override
    public String getSql( String type,String stateId, String transactionTimeStart, String transactionTimeEnd) {
        StringBuilder sb = new StringBuilder(" SELECT T0.ID,T0.CONTENT,T0.MONEY,T0.TRANSACTIONTIME,t0.state,t0.FUNDID,T0.REASON,t0.RATE,t0.OTHERTYPE,t0.FINANIAL,T1.NAME,T2.NAME AS typetwoparentName,T3.NAME as typeoneparentNAME,T4.NAME AS STATENAME FROM P_MONEY_FUNDS T0 ");
        sb.append(" LEFT JOIN B_ENUM_FUNDS T1 ON T0.FUNDID=T1.ID ");
        sb.append(" LEFT JOIN B_ENUM_FUNDS T2 ON T1.PARENTID=T2.ID ");
        sb.append(" LEFT JOIN B_ENUM_FUNDS T3 ON T2.PARENTID=T3.ID ");
        sb.append(" LEFT JOIN B_COUNT T4 ON T0.STATE=T4.ID AND T4.TYPE='STATE' ");
        sb.append(" where 1=1 ");
        if (!"".equals(stateId) && null != stateId) {
            sb.append(" and t0.state='").append(stateId).append("' ");
        }
        if (!"".equals(type) && null != type) {
            sb.append(" and t0.fundId IN ( SELECT  T0.ID FROM B_ENUM_FUNDS T0 LEFT JOIN B_ENUM_FUNDS T1 ON T0.PARENTID = T1. ID WHERE T0. parentID = '").append(type).append("' OR t1. parentID = '").append(type).append("' OR t0. ID = '").append(type).append("')");
        }
        if (!"".equals(transactionTimeStart) && null != transactionTimeStart) {
            sb.append(" and t0.TRANSACTIONTIME>=to_date('").append(transactionTimeStart).append("','yyyy-mm-dd')");
        }
        if (!"".equals(transactionTimeEnd) && null != transactionTimeEnd) {
            sb.append(" and t0.TRANSACTIONTIME<=to_date('").append(transactionTimeEnd).append(" 23:59:59', 'yyyy-mm-dd hh24:mi:ss')");
        }
        sb.append(" order by t0.TRANSACTIONTIME desc ");
        return sb.toString();
    }

    @Override
    public Integer getTotal(String sql) {
        return jdbcTemplate.queryForObject(CommonUtils.setCount(sql), Integer.class);
    }

    @Override
    public List<MoneyFundsDto> getRows(int rn, int rowNumber, String sql) {
        final List<MoneyFundsDto> list = new ArrayList<>();
        jdbcTemplate.query(CommonUtils.setPage(sql, rn, rowNumber), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                MoneyFundsDto moneyFundsDto = new MoneyFundsDto();
                moneyFundsDto.setId(resultSet.getString("ID"));
                moneyFundsDto.setContent(resultSet.getString("CONTENT"));
                moneyFundsDto.setTransactionTime(CommonUtils.getYMD(resultSet.getString("TRANSACTIONTIME")));
                moneyFundsDto.setStateName(resultSet.getString("STATENAME"));
                moneyFundsDto.setReason(resultSet.getString("REASON"));
                moneyFundsDto.setTypeName(resultSet.getString("NAME"));
                moneyFundsDto.setTypeTwoParentName(resultSet.getString("TYPETWOPARENTNAME"));
                moneyFundsDto.setTypeOneParentName(resultSet.getString("TYPEONEPARENTNAME"));
                moneyFundsDto.setRate(resultSet.getFloat("RATE"));
                moneyFundsDto.setFinanial(resultSet.getString("FINANIAL"));
                moneyFundsDto.setOtherType(resultSet.getString("OTHERTYPE"));
                moneyFundsDto.setFundId(resultSet.getString("FUNDID"));
                moneyFundsDto.setState(resultSet.getString("STATE"));
                moneyFundsDto.setMoney(resultSet.getDouble("MONEY"));
                //支出
                if ("支出".equals(resultSet.getString("TYPEONEPARENTNAME"))) {
                    moneyFundsDto.setMoneyText("-" + resultSet.getString("MONEY"));
                } else {
                    moneyFundsDto.setMoneyText("+" + resultSet.getString("MONEY"));
                }
                list.add(moneyFundsDto);
            }
        });
        return list;
    }

    @Override
    public List<Map<String, Object>> getState() {
        StringBuilder sb = new StringBuilder("select id,name from B_COUNT where type='STATE'");
        final List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", "");
        map.put("name", "-请选择-");
        list.add(map);
        jdbcTemplate.query(sb.toString(), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Map<String, Object> map = new HashMap<>();
                map.put("id", resultSet.getString("ID"));
                map.put("name", resultSet.getString("NAME"));
                list.add(map);
            }
        });
        return list;
    }




    @Override
    public List<Map<String, Object>> getFinanial() {
        StringBuilder sb = new StringBuilder("select id,name from  B_COUNT where TYPE ='FINANIAL'");
        final List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", "");
        map.put("name", "-请选择-");
        list.add(map);
        jdbcTemplate.query(sb.toString(), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Map<String, Object> map = new HashMap<>();
                map.put("id", resultSet.getString("ID"));
                map.put("name", resultSet.getString("NAME"));
                list.add(map);
            }
        });
        return list;
    }

    @Override
    public List<Map<String, Object>> getOtherType() {
        StringBuilder sb = new StringBuilder("select id,name from  B_COUNT where TYPE ='DATE'");
        final List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", "");
        map.put("name", "-请选择-");
        list.add(map);
        jdbcTemplate.query(sb.toString(), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Map<String, Object> map = new HashMap<>();
                map.put("id", resultSet.getString("ID"));
                map.put("name", resultSet.getString("NAME"));
                list.add(map);
            }
        });
        return list;
    }

    @Override
    public boolean insertOrUpdate(String id, String fundId, String money, String stateId, String transactionTime, String content, String reason, String rate, String finanial, String otherType, HttpServletRequest request) {
        //判断id，是否非新增或修改
        StringBuilder sb=new StringBuilder("");
        if("".equals(id)||null==id){
            String primaryKey= UUID.randomUUID().toString().replace("-","");
            sb.append("insert into P_MONEY_FUNDS (ID,FUNDID,MONEY,CONTENT,TRANSACTIONTIME,CREATETIME,RATE,OTHERTYPE,FINANIAL,STATE,REASON)values(");
            sb.append("'").append(primaryKey).append("',");
            sb.append("'").append(fundId).append("',");
            sb.append("'").append(money).append("',");
            sb.append("'").append(content).append("',");
            sb.append("to_date('").append(transactionTime).append("','yyyy-mm-dd'),");
            sb.append("to_date('").append(CommonUtils.getCurrentTime()).append("','yyyy-mm-dd hh24:mi:ss'),");
            sb.append("'").append(rate).append("',");
            sb.append("'").append(otherType).append("',");
            sb.append("'").append(finanial).append("',");
            sb.append("'").append(stateId).append("',");
            sb.append("'").append(reason).append("')");
            jdbcTemplate.execute(sb.toString());
            operLogService.insertOperLog(request, EnumPermission.FUND_ADD);
        }else {
            sb.append("update P_MONEY_FUNDS set ");
            sb.append(" FUNDID='").append(fundId).append("',");
            sb.append(" MONEY='").append(money).append("',");
            sb.append(" CONTENT='").append(content).append("',");
            sb.append(" TRANSACTIONTIME= to_date('").append(transactionTime).append("','yyyy-mm-dd'),");
            sb.append(" RATE='").append(rate).append("',");
            sb.append(" OTHERTYPE='").append(otherType).append("',");
            sb.append(" FINANIAL='").append(finanial).append("',");
            sb.append(" STATE='").append(stateId).append("',");
            sb.append(" REASON='").append(reason).append("'");
            sb.append(" where id='").append(id).append("'");
            jdbcTemplate.execute(sb.toString());
            operLogService.insertOperLog(request,EnumPermission.FUND_UPDATE);
        }
        return true;
    }
}
