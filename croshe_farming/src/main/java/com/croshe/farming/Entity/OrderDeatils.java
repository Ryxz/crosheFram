package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by ZPA on 2017/6/28.
 */
public class OrderDeatils implements Serializable {

    private int standardId;
    private int price;
    private int count;
    private String orderCode;
    private int orderDeatilsId;
    private int shopId;
    private String orderDeatilsDateTime;
    private int orderDeatilsType;
    private int targetId;
    private int totalPrice;
    private ProductInfo product;
    private PackageInfo packagess;
    private StandInfo standard;

    public PackageInfo getPackagess() {
        return packagess;
    }

    public void setPackagess(PackageInfo packagess) {
        this.packagess = packagess;
    }

    public StandInfo getStandard() {
        return standard;
    }

    public void setStandard(StandInfo standard) {
        this.standard = standard;
    }

    public int getStandardId() {
        return standardId;
    }

    public void setStandardId(int standardId) {
        this.standardId = standardId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getOrderDeatilsId() {
        return orderDeatilsId;
    }

    public void setOrderDeatilsId(int orderDeatilsId) {
        this.orderDeatilsId = orderDeatilsId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getOrderDeatilsDateTime() {
        return orderDeatilsDateTime;
    }

    public void setOrderDeatilsDateTime(String orderDeatilsDateTime) {
        this.orderDeatilsDateTime = orderDeatilsDateTime;
    }

    public int getOrderDeatilsType() {
        return orderDeatilsType;
    }

    public void setOrderDeatilsType(int orderDeatilsType) {
        this.orderDeatilsType = orderDeatilsType;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ProductInfo getProduct() {
        return product;
    }

    public void setProduct(ProductInfo product) {
        this.product = product;
    }
}
