package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */

public class FramDetailsInfo implements Serializable {
//"targetNumber": "22",
//        "price": "23",
//        "detailsId": 5,
//        "reason": null,
//        "name": "辣椒种子",
//        "detailsPackageId": 4,
//        "img": "attachments/productSmallImage/b9c445a4624b87e2e8b3387dc1f5f85d.png",
//        "detailsTime": "2017-06-06 15:01:38",
//        "targetCode": null,
//        "targetId": 1,
//        "detailsProductId": 1,
//        "img": 1
//    "targetNumber": "22",
//            "price": "23",
//            "detailsId": 5,
//            "reason": null,
//            "name": "辣椒种子",
//            "detailsPackageId": 4,
//            "img": "attachments/productSmallImage/b9c445a4624b87e2e8b3387dc1f5f85d.png",
//            "detailsTime": "2017-06-06 15:01:38",
//            "targetCode": null,
//            "targetId": 1,
//            "detailsProductId": 1,
//            "targetType": 1
    private ProductInfo product;
    private String name;
    private String img;
    private String detailsId;
    private String detailsProductId;
    private String detailsPackageId;
    private String detailsTime;
    private String targetId;
    private String targetType;
    private String targetCode;
    private String targetNumber;
    private String price;
    private String reason;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductInfo getProduct() {
        return product;
    }

    public void setProduct(ProductInfo product) {
        this.product = product;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(String detailsId) {
        this.detailsId = detailsId;
    }

    public String getDetailsProductId() {
        return detailsProductId;
    }

    public void setDetailsProductId(String detailsProductId) {
        this.detailsProductId = detailsProductId;
    }

    public String getDetailsPackageId() {
        return detailsPackageId;
    }

    public void setDetailsPackageId(String detailsPackageId) {
        this.detailsPackageId = detailsPackageId;
    }

    public String getDetailsTime() {
        return detailsTime;
    }

    public void setDetailsTime(String detailsTime) {
        this.detailsTime = detailsTime;
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

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
