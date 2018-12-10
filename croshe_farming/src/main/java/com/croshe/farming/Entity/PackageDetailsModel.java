package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/30.
 */
/*
*                       "product":Object{...},
                        "targetNumber":null,
                        "price":null,
                        "detailsId":9,
                        "reason":"好搭档",
                        "name":"辣椒种子",
                        "detailsPackageId":6,
                        "img":"attachments/productSmallImage/51cda6abc4bda6e09fc18edc3525cb9a.png",
                        "detailsTime":"2017-06-12 09:31:47",
                        "targetCode":"00000000012",
                        "targetId":12,
                        "detailsProductId":null,
                        "targetType":1
* */
public class PackageDetailsModel implements Serializable{
    /**
     * targetNumber : null
     * price : null
     * detailsId : 9
     * reason : 好搭档
     * name : 辣椒种子
     * detailsPackageId : 6
     * img : attachments/productSmallImage/51cda6abc4bda6e09fc18edc3525cb9a.png
     * detailsTime : 2017-06-12 09:31:47
     * targetCode : 00000000012
     * targetId : 12
     * detailsProductId : null
     * targetType : 1
     */
    ProductInfo product;
    private String targetNumber;
    private String price;
    private String detailsId;
    private String reason;
    private String name;
    private String detailsPackageId;
    private String img;
    private String detailsTime;
    private String targetCode;
    private String targetId;
    private String detailsProductId;
    private String targetType;

    public ProductInfo getProduct() {
        return product;
    }

    public void setProduct(ProductInfo product) {
        this.product = product;
    }

    public String getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(String targetNumber) {
        this.targetNumber = targetNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(String detailsId) {
        this.detailsId = detailsId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetailsPackageId() {
        return detailsPackageId;
    }

    public void setDetailsPackageId(String detailsPackageId) {
        this.detailsPackageId = detailsPackageId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDetailsTime() {
        return detailsTime;
    }

    public void setDetailsTime(String detailsTime) {
        this.detailsTime = detailsTime;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getDetailsProductId() {
        return detailsProductId;
    }

    public void setDetailsProductId(String detailsProductId) {
        this.detailsProductId = detailsProductId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }
}
