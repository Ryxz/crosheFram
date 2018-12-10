package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class PackageInfo implements Serializable {
//      "packageTopTime": null,
//              "packageDetails": "特征",
//              "imgs":
//              "packageType": 0,
//              "packageName": "套餐1",
//              "packagePrice": 121.0,
//              "packageId": 3,
//              "packageBuyKnow": "详情",
//              "packageLife": 1212.0,
//              "productId": null,
//              "packageTargetType": 0,
//              "packageImage": "attachments/packageImage/a2cbf01918b75f0b3786d31c21e604ee.png",
//              "packageWeight": 111.0,
//              "details":
//              "packageSales": 111,
//              "packageSave": 111.0,
//              "packageTop": 0,
//              "packageVarieties": "121"
    private String packageTopTime;
    private String packageDetails;
    private List<String> imgs;
    private int packageType;
    private String packageName;
    private String packagePrice;
    private String packageId;
    private String packageBuyKnow;
    private String  packageLife;
    private String productId;
    private String packageTargetType;
    private String packageImage;
    private String packageWeight;
    private List<PackageDetailsEntity> details;
    private String packageSales;
    private String packageSave;
    private String packageTop;
    private String packageVarieties;

    private String countComment;
    private List<EvaluationInfo> comments;

    public String getCountComment() {
        return countComment;
    }

    public void setCountComment(String countComment) {
        this.countComment = countComment;
    }

    public List<EvaluationInfo> getComments() {
        return comments;
    }

    public void setComments(List<EvaluationInfo> comments) {
        this.comments = comments;
    }

    public String getPackageTopTime() {
        return packageTopTime;
    }

    public void setPackageTopTime(String packageTopTime) {
        this.packageTopTime = packageTopTime;
    }

    public String getPackageDetails() {
        return packageDetails;
    }

    public void setPackageDetails(String packageDetails) {
        this.packageDetails = packageDetails;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public int getPackageType() {
        return packageType;
    }

    public void setPackageType(int packageType) {
        this.packageType = packageType;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageBuyKnow() {
        return packageBuyKnow;
    }

    public void setPackageBuyKnow(String packageBuyKnow) {
        this.packageBuyKnow = packageBuyKnow;
    }

    public String getPackageLife() {
        return packageLife;
    }

    public void setPackageLife(String packageLife) {
        this.packageLife = packageLife;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPackageTargetType() {
        return packageTargetType;
    }

    public void setPackageTargetType(String packageTargetType) {
        this.packageTargetType = packageTargetType;
    }

    public String getPackageImage() {
        return packageImage;
    }

    public void setPackageImage(String packageImage) {
        this.packageImage = packageImage;
    }

    public String getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(String packageWeight) {
        this.packageWeight = packageWeight;
    }

    public List<PackageDetailsEntity> getDetails() {
        return details;
    }

    public void setDetails(List<PackageDetailsEntity> details) {
        this.details = details;
    }

    public String getPackageSales() {
        return packageSales;
    }

    public void setPackageSales(String packageSales) {
        this.packageSales = packageSales;
    }

    public String getPackageSave() {
        return packageSave;
    }

    public void setPackageSave(String packageSave) {
        this.packageSave = packageSave;
    }

    public String getPackageTop() {
        return packageTop;
    }

    public void setPackageTop(String packageTop) {
        this.packageTop = packageTop;
    }

    public String getPackageVarieties() {
        return packageVarieties;
    }

    public void setPackageVarieties(String packageVarieties) {
        this.packageVarieties = packageVarieties;
    }
}
