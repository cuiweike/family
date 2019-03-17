package com.family.utils;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {


    /**
     * 设置分页sql
     * zjc  2019/02/09
     *
     * @param sql
     * @param r         当前页码
     * @param rowNumber 当前页数据数
     * @return 返回分页sql
     */
    public static String setPage(String sql, int r, int rowNumber) {
        //起始行
        int rn = (r - 1) * rowNumber + 1;
        //终止行
        int num = r * rowNumber;
        StringBuilder sb = new StringBuilder(" select * from (");
        sb.append(" (select rownum rn,uo.* from ");
        sb.append(" (").append(sql).append(") uo");
        sb.append(" where rownum<= ").append(num).append(" ))ua ");
        sb.append(" where ua.rn>= ").append(rn);

        return sb.toString();
    }

    /**
     * 设置记录总数sql
     * 2019/02/09
     *
     * @param sql
     * @return 返回记录总数sql
     */
    public static String setCount(String sql) {
        StringBuilder sb = new StringBuilder("select count(*) from (").append(sql).append(")");
        return sb.toString();
    }

    /**
     * 返回当前时间:年月日时分秒
     * 2019/02/10
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long now = System.currentTimeMillis();
        return sdf.format(now);
    }

    public static String getYMD(String str) {
        SimpleDateFormat sdf =null;
        Date date =null;
        //yyyy-MM-dd HH:mm:ss
        if (str.split("-").length == 3) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        SimpleDateFormat sdfResult = new SimpleDateFormat("yyyy-MM-dd");
        return sdfResult.format(date);
    }

    /**
     * 获取md5加密
     *
     * @param str 传入字符串
     * @return
     * @author zjc 2019/02/11
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 计算md5函数
            messageDigest.update(str.getBytes());
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
