package com.croshe.farming.Entity;

/**
 * Created by Administrator on 2017/6/7.
 */

public class TypeInfo {
// "typeTop":0,
//         "typeType":1,
//         "typeName":"鸡",
//         "productTypeLayerCode":"0000000009",
//         "parentId":1,
//         "typeSubtitle":null,
//         "typeDateTime":"2017-06-03 14:00:38",
//         "typeImage":null,
//         "typeClass":null,
//         "typeId":9
    private String typeTop;
    private String typeType;
    private String typeName;
    private String productTypeLayerCode;
    private int parentId;
    private String typeSubtitle;
    private String typeDateTime;
    private String typeImage;
    private String typeClass;
    private int typeId;
    private boolean ischeck = false ;
    private boolean isAdd = false;

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getTypeTop() {
        return typeTop;
    }

    public void setTypeTop(String typeTop) {
        this.typeTop = typeTop;
    }

    public String getTypeType() {
        return typeType;
    }

    public void setTypeType(String typeType) {
        this.typeType = typeType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getProductTypeLayerCode() {
        return productTypeLayerCode;
    }

    public void setProductTypeLayerCode(String productTypeLayerCode) {
        this.productTypeLayerCode = productTypeLayerCode;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getTypeSubtitle() {
        return typeSubtitle;
    }

    public void setTypeSubtitle(String typeSubtitle) {
        this.typeSubtitle = typeSubtitle;
    }

    public String getTypeDateTime() {
        return typeDateTime;
    }

    public void setTypeDateTime(String typeDateTime) {
        this.typeDateTime = typeDateTime;
    }

    public String getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(String typeImage) {
        this.typeImage = typeImage;
    }

    public String getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
