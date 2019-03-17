package com.family.springboot.money.dto;

import java.util.Date;

public class MoneyFundsDto {
    private String id;
    private Double money;
    private String moneyText;
    private String type;
    private String typeName;
    private String typeOneParent;
    private String TypeOneParentName;
    private String typeTwoParent;
    private String typeTwoParentName;
    private String content;
    private String transactionTime;
    private String createTime;
    private Float rate;
    private String otherType;
    private String otherTypeName;
    private String finanial;
    private String finanialName;
    private String state;
    private String stateName;
    private String reason;
    private String fundId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getOtherType() {
        return otherType;
    }

    public void setOtherType(String otherType) {
        this.otherType = otherType;
    }

    public String getOtherTypeName() {
        return otherTypeName;
    }

    public void setOtherTypeName(String otherTypeName) {
        this.otherTypeName = otherTypeName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTypeOneParent() {
        return typeOneParent;
    }

    public void setTypeOneParent(String typeOneParent) {
        this.typeOneParent = typeOneParent;
    }

    public String getTypeOneParentName() {
        return TypeOneParentName;
    }

    public void setTypeOneParentName(String typeOneParentName) {
        TypeOneParentName = typeOneParentName;
    }

    public String getTypeTwoParent() {
        return typeTwoParent;
    }

    public void setTypeTwoParent(String typeTwoParent) {
        this.typeTwoParent = typeTwoParent;
    }

    public String getTypeTwoParentName() {
        return typeTwoParentName;
    }

    public void setTypeTwoParentName(String typeTwoParentName) {
        this.typeTwoParentName = typeTwoParentName;
    }

    public String getMoneyText() {
        return moneyText;
    }

    public void setMoneyText(String moneyText) {
        this.moneyText = moneyText;
    }

    public String getFinanial() {
        return finanial;
    }

    public void setFinanial(String finanial) {
        this.finanial = finanial;
    }

    public String getFinanialName() {
        return finanialName;
    }

    public void setFinanialName(String finanialName) {
        this.finanialName = finanialName;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    @Override
    public String toString() {
        return "MoneyFundsDto{" +
                "id='" + id + '\'' +
                ", money=" + money +
                ", moneyText='" + moneyText + '\'' +
                ", type='" + type + '\'' +
                ", typeName='" + typeName + '\'' +
                ", typeOneParent='" + typeOneParent + '\'' +
                ", TypeOneParentName='" + TypeOneParentName + '\'' +
                ", typeTwoParent='" + typeTwoParent + '\'' +
                ", typeTwoParentName='" + typeTwoParentName + '\'' +
                ", content='" + content + '\'' +
                ", transactionTime=" + transactionTime +
                ", createTime=" + createTime +
                ", rate=" + rate +
                ", otherType='" + otherType + '\'' +
                ", otherTypeName='" + otherTypeName + '\'' +
                ", finanial='" + finanial + '\'' +
                ", finanialName='" + finanialName + '\'' +
                ", state='" + state + '\'' +
                ", stateName='" + stateName + '\'' +
                ", reason='" + reason + '\'' +
                ", fundId='" + fundId + '\'' +
                '}';
    }
}
