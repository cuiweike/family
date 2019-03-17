package com.family.springboot.system.service;

import com.family.springboot.system.dto.OperLogDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface OperLogService {

    /**
     * insert操作日志表
     *
     * @param request
     * @param button    枚举类常量
     */
    void insertOperLog(HttpServletRequest request,String button);

    String getSql(String userName, String view, String button, String operTimeStart, String operTimeEnd);

    Integer getCount(String sql);

    List<OperLogDto> getRows(String sql, int rn, int rowNumber);

    /**
     * 获取操作模块下拉框
     *
     * @return
     */
    List<Map<String,Object>> getModule();


    /**
     * 获取操作按钮下拉框
     *
     * @return
     * @param module
     */
    List<Map<String,Object>> getButton(String module);
}
