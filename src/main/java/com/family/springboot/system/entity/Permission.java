package com.family.springboot.system.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Permission {
    private String permissionId;
    private String permissionName;
    private String parentId;
    private String state;
    private String path;
    private String sort;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date deleteTime;
    private String mark;

    public Permission(String permissionId, String permissionName, String parentId, String state, String path, String sort, Date createTime, Date deleteTime, String mark) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
        this.parentId = parentId;
        this.state = state;
        this.path = path;
        this.sort = sort;
        this.createTime = createTime;
        this.deleteTime = deleteTime;
        this.mark = mark;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId='" + permissionId + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", parentId='" + parentId + '\'' +
                ", state='" + state + '\'' +
                ", path='" + path + '\'' +
                ", sort='" + sort + '\'' +
                ", createTime=" + createTime +
                ", deleteTime=" + deleteTime +
                ", mark='" + mark + '\'' +
                '}';
    }
}
