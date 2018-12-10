package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/7.
 */
/*
 "standardId":51,
        "carId":1,
        "standard":Object{...},
        "img":"attachments/merchantTBHYZGZ/4cf5b945c1e2e3207ccd72a13dcc0d4b.jpg",
        "targetId":43,
        "product":Object{...},
        "shopCarLayerCode":null,
        "carState":0,
        "productCount":11,
        "name":"驱虫药",
        "userId":4,
        "carDateTime":"2017-07-05 21:09:44",
        "productPrice":11,
        "targetType":1,
        "orderId":null
 */
public class BuyCarInfo  implements Serializable {
    private boolean isselecte;
    private StandInfo standard;
    private String img;
    private String carId;
    private String shopCarLayerCode;
    private String userId;
    private String productPrice;
    private String productCount;
    private String standardId;
    private String orderId;
    private String carState;
    private String carDateTime;
    private String targetId;
    private String targetType;
    private String productName;
    private String name;
    private ProductInfo product;
    private int num = 1;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ProductInfo getProduct() {
        return product;
    }

    public void setProduct(ProductInfo product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public boolean isselecte() {
        return isselecte;
    }

    public void setIsselecte(boolean isselecte) {
        this.isselecte = isselecte;
    }

    public StandInfo getStandard() {
        return standard;
    }

    public void setStandard(StandInfo standard) {
        this.standard = standard;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getShopCarLayerCode() {
        return shopCarLayerCode;
    }

    public void setShopCarLayerCode(String shopCarLayerCode) {
        this.shopCarLayerCode = shopCarLayerCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getCarDateTime() {
        return carDateTime;
    }

    public void setCarDateTime(String carDateTime) {
        this.carDateTime = carDateTime;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }
}
