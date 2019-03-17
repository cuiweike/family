package com.family.springboot.money.service;

import com.family.springboot.money.dto.MoneyFundsDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface MoneyFundsService {
    /**
     * 获取查询sql
     * 2019/02/17
     *
     * @param stateId              状态id
     * @param type                 当前类型
     * @param transactionTimeStart 交易时间
     * @param transactionTimeEnd   交易时间至
     * @return
     */
    String getSql(String type, String stateId, String transactionTimeStart, String transactionTimeEnd);

    /**
     * 获取记录总数
     *
     * @param sql
     * @return
     */
    Integer getTotal(String sql);

    /**
     * 获取数据
     *
     * @param rn        页码
     * @param rowNumber 数据条数
     * @param sql
     * @return
     */
    List<MoneyFundsDto> getRows(int rn, int rowNumber, String sql);

    /**
     * 获取状态下拉框
     *
     * @return
     */
    List<Map<String, Object>> getState();


    /**
     * 获取交易方
     *
     * @return
     */
    List<Map<String,Object>> getFinanial();

    /**
     * 获取期数
     *
     * @return
     */
    List<Map<String,Object>> getOtherType();

    /**
     * 新增或修改方法
     *
     * @param id                id
     * @param fundId            枚举表id
     * @param money             金额
     * @param stateId           状态
     * @param transactionTime   交易时间
     * @param content           详细内容
     * @param reason            作废原因
     * @param rate              利率
     * @param finanial          交易方
     * @param otherType         其他状态：期数
     * @param request
     * @return
     */
    boolean insertOrUpdate(String id, String fundId, String money, String stateId, String transactionTime, String content, String reason, String rate, String finanial, String otherType, HttpServletRequest request);
}
