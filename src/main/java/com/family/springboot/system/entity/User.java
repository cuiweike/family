package com.family.springboot.system.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.family.common.pojo.Count;

import java.util.Date;

public class User {
    private String userId;
    private String userName;
    private String passWord;
    private Integer state;
    private Count sex;
    @JSONField (format= "yyyy-MM-dd HH:mm:ss" )
    private Date brithday;
    @JSONField (format= "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;
    @JSONField (format= "yyyy-MM-dd HH:mm:ss" )
    private Date deleteTime;
    private Role role;
    private String sexName;

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Count getSex() {
        return sex;
    }

    public void setSex(Count sex) {
        this.sex = sex;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", state=" + state +
                ", sex='" + sex + '\'' +
                ", brithday=" + brithday +
                ", createTime=" + createTime +
                ", deleteTime=" + deleteTime +
                ", role=" + role +
                '}';
    }
}
