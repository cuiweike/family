package com.family.springboot.system.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class LogOper {
    private String id;
    private User user;
    private String view;
    private String button;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date operTime;

    public LogOper(String id, User user, String view, String button, Date operTime) {
        this.id = id;
        this.user = user;
        this.view = view;
        this.button = button;
        this.operTime = operTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    @Override
    public String toString() {
        return "LogOper{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", view='" + view + '\'' +
                ", button='" + button + '\'' +
                ", operTime=" + operTime +
                '}';
    }
}
