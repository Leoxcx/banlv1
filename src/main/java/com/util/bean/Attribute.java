package com.util.bean;

import java.io.Serializable;

public class Attribute implements Serializable {
    private int attrId;// 属性表主键
    private String attrTable;// 属性所属表
    private String attrValue;//属性
    private String attrName;//属性名
    private boolean attrShow;// 是否显示属性

    public Attribute() {
    }

    public Attribute(int attrId, String attrTable, String attrValue, String attrName, boolean attrShow) {
        this.attrId = attrId;
        this.attrTable = attrTable;
        this.attrValue = attrValue;
        this.attrName = attrName;
        this.attrShow = attrShow;
    }

    public int getAttrId() {
        return attrId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    public String getAttrTable() {
        return attrTable;
    }

    public void setAttrTable(String attrTable) {
        this.attrTable = attrTable;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public boolean getAttrShow() {
        return attrShow;
    }

    public void setAttrShow(boolean attrShow) {
        this.attrShow = attrShow;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "attrId=" + attrId +
                ", attrTable='" + attrTable + '\'' +
                ", attrValue='" + attrValue + '\'' +
                ", attrName='" + attrName + '\'' +
                ", attrShow=" + attrShow +
                '}';
    }
}
