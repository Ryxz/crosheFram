package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/28.
 */

public class PackageDetailsEntity implements Serializable{


    /**
     * standardId : 4
     * targetNumber : 12
     * reason : 12
     * detailsPackageId : 1
     * img : attachments/merchantTBHYZGZ/040ed5cce925fa15fd593583ce754155.png
     * targetCode : null
     * targetId : 11
     * price : 12
     * detailsId : 20
     * name : 生菜，250g一份
     * detailsTime : 2017-07-28 19:07:16
     * targetType : 1
     */

    private int standardId;
    private String targetNumber;
    private String reason;
    private int detailsPackageId;
    private String img;
    private Object targetCode;
    private int targetId;
    private String price;
    private int detailsId;
    private String name;
    private String detailsTime;
    private int targetType;

    public int getStandardId() {
        return standardId;
    }

    public void setStandardId(int standardId) {
        this.standardId = standardId;
    }

    public String getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(String targetNumber) {
        this.targetNumber = targetNumber;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getDetailsPackageId() {
        return detailsPackageId;
    }

    public void setDetailsPackageId(int detailsPackageId) {
        this.detailsPackageId = detailsPackageId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Object getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(Object targetCode) {
        this.targetCode = targetCode;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetailsTime() {
        return detailsTime;
    }

    public void setDetailsTime(String detailsTime) {
        this.detailsTime = detailsTime;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }
}
