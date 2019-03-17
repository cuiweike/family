package com.family.springboot.system.web;

import com.alibaba.fastjson.JSON;
import com.family.springboot.system.dto.OperLogDto;
import com.family.springboot.system.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/operLogController")
@RestController
public class OperLogController {

    @Autowired
    private OperLogService operLogService;

    @RequestMapping("/getData")
    public String getData(String userName, String view, String button, String operTimeStart, String operTimeEnd, HttpServletRequest request){
        String sql=operLogService.getSql(userName,view,button,operTimeStart,operTimeEnd);
        Integer count=operLogService.getCount(sql);
        int rn = Integer.parseInt(request.getParameter("page"));
        int rowNumber = Integer.parseInt(request.getParameter("rows"));
        List<OperLogDto> rows=operLogService.getRows(sql,rn,rowNumber);
        Map<String,Object> map=new HashMap<>();
        map.put("total",count);
        map.put("rows",rows);
        return JSON.toJSONString(map);
    }

    @RequestMapping("/getModule")
    public String getModule(){
        List<Map<String,Object>> list=operLogService.getModule();
        return JSON.toJSONString(list);
    }
    @RequestMapping("/getButton")
    public String getButton(String module){
        List<Map<String,Object>> list=operLogService.getButton(module);
        return JSON.toJSONString(list);
    }
}
