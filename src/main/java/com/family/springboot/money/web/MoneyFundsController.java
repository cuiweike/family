package com.family.springboot.money.web;

import com.alibaba.fastjson.JSON;
import com.family.springboot.money.dto.MoneyFundsDto;
import com.family.springboot.money.service.EnumFundsService;
import com.family.springboot.money.service.MoneyFundsService;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/moneyFundsController")
@RestController
public class MoneyFundsController {

    @Autowired
    private MoneyFundsService moneyFundsService;
    @Autowired
    private EnumFundsService enumFundsService;

    @RequestMapping("/getData")
    public String getData( String type,String stateId, String transactionTimeStart, String transactionTimeEnd, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            String sql = moneyFundsService.getSql(type,stateId, transactionTimeStart, transactionTimeEnd);

            Integer total = moneyFundsService.getTotal(sql);

            int rn = Integer.parseInt(request.getParameter("page"));
            int rowNumber = Integer.parseInt(request.getParameter("rows"));

            List<MoneyFundsDto> rows = moneyFundsService.getRows(rn, rowNumber, sql);
            map.put("total", total);
            map.put("rows", rows);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/getTypeTree")
    public String getTypeTree() {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Map<String, Object> map = new HashMap<>(1);
            map.put("id", "");
            map.put("text", "-请选择-");
            list.add(map);
            List<Map<String, Object>> list1 = enumFundsService.getData();
            for (Map<String, Object> m : list1) {
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(list);
    }

    @RequestMapping("/getState")
    public String getState() {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            list = moneyFundsService.getState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(list);
    }

    @RequestMapping("/getFinanial")
    public String getFinanial(){
        List<Map<String,Object>> list=new ArrayList<>();
        try {
            list=moneyFundsService.getFinanial();
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(list);
    }

    @RequestMapping("/getOtherType")
    public String getTypeTwoParentId(){
        List<Map<String,Object>> list=new ArrayList<>();
        try {
            list=moneyFundsService.getOtherType();
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(list);
    }

    @RequestMapping("/insertOrUpdate")
    public boolean insertOrUpdate(String id,String fundId,String money,String stateId,String transactionTime,String content,String reason,String rate,String finanial,String otherType,HttpServletRequest request){
        boolean flag=false;
        try {
            flag=moneyFundsService.insertOrUpdate(id,fundId,money,stateId,transactionTime,content,reason,rate,finanial,otherType,request);
        }catch (Exception e){
            e.printStackTrace();
            return flag;
        }
        return flag;

    }


}
