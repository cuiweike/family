package com.family.springboot.system.dto;

import java.util.Date;

public class OperLogDto {
    private String id;
    private String userId;
    private String userName;
    private String view;
    private String viewName;
    private String button;
    private String buttonName;
    private String operTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    @Override
    public String toString() {
        return "OperLogDto{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", view='" + view + '\'' +
                ", viewName='" + viewName + '\'' +
                ", button='" + button + '\'' +
                ", buttonName='" + buttonName + '\'' +
                ", operTime=" + operTime +
                '}';
    }
}
