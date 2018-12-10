package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/5.
 */

public class CropEntity implements Serializable {

    /**
     * standardId : 38
     * weight : 0
     * weightUnitStr : 斤
     * weightUnit : 0
     * storeTime : 2017-09-05 17:38:29
     * number : 0
     * typeClass : null
     * productId : 54
     * typeType : null
     * numberUnitStr : 斤
     * price : 0
     * userId : 7
     * useArea : null
     * storeType : 1
     * numberUnit : 0
     * storeId : 435
     */

    private int standardId;
    private int weight;
    private String weightUnitStr;
    private int weightUnit;
    private String storeTime;
    private int number;
    private String typeClass;
    private int productId;
    private String typeType;
    private String numberUnitStr;
    private int price;
    private int userId;
    private String useArea;
    private int storeType;
    private int numberUnit;
    private int storeId;
    private ProductEntity product;

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getStandardId() {
        return standardId;
    }

    public void setStandardId(int standardId) {
        this.standardId = standardId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getWeightUnitStr() {
        return weightUnitStr;
    }

    public void setWeightUnitStr(String weightUnitStr) {
        this.weightUnitStr = weightUnitStr;
    }

    public int getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(int weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(String storeTime) {
        this.storeTime = storeTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTypeType() {
        return typeType;
    }

    public void setTypeType(String typeType) {
        this.typeType = typeType;
    }

    public String getNumberUnitStr() {
        return numberUnitStr;
    }

    public void setNumberUnitStr(String numberUnitStr) {
        this.numberUnitStr = numberUnitStr;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUseArea() {
        return useArea;
    }

    public void setUseArea(String useArea) {
        this.useArea = useArea;
    }

    public int getStoreType() {
        return storeType;
    }

    public void setStoreType(int storeType) {
        this.storeType = storeType;
    }

    public int getNumberUnit() {
        return numberUnit;
    }

    public void setNumberUnit(int numberUnit) {
        this.numberUnit = numberUnit;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
