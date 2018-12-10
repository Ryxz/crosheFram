package com.croshe.farming.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */

/*
*               "packageTopTime":null,
                "packageDetails":null,
                "packageType":null,
                "packageName":"科学搭配",
                "packagePrice":111,
                "packageId":6,
                "packageBuyKnow":null,
                "packageDetailsModels":Array[3],
                "packageLife":null,
                "productId":12,
                "packageTargetType":1,
                "packageImage":null,
                "packageWeight":null,
                "packageSales":null,
                "packageSave":20,
                "packageTop":0,
                "packageVarieties":null
            }
* */
public class PackageModel implements Serializable{

    private String packagePrice;
    private String packageSave;
    private List<PackageDetailsModel> packageDetailsModels;
    /**
     * packageTopTime : 2017-07-22 10:48:41
     * packageDetails : null
     * packageType : null
     * packageName : 草
     * packagePrice : 23
     * packageId : 23
     * packageBuyKnow : null
     * packageLife : null
     * productId : 50
     * packageTargetType : 1
     * packageImage : null
     * packageWeight : null
     * packageSales : null
     * packageSave : 12
     * packageTop : 1
     * packageVarieties : nul
     */

    private String packageTopTime;
    private Object packageDetails;
    private Object packageType;
    private String packageName;
    private int packageId;
    private Object packageBuyKnow;
    private Object packageLife;
    private String productId;
    private int packageTargetType;
    private Object packageImage;
    private Object packageWeight;
    private Object packageSales;
    private int packageTop;
    private String packageVarieties;

    public List<PackageDetailsModel> getPackageDetailsModels() {
        return packageDetailsModels;
    }

    public void setPackageDetailsModels(List<PackageDetailsModel> packageDetailsModels) {
        this.packageDetailsModels = packageDetailsModels;
    }

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getPackageSave() {
        return packageSave;
    }

    public void setPackageSave(String packageSave) {
        this.packageSave = packageSave;
    }

    public String getPackageTopTime() {
        return packageTopTime;
    }

    public void setPackageTopTime(String packageTopTime) {
        this.packageTopTime = packageTopTime;
    }

    public Object getPackageDetails() {
        return packageDetails;
    }

    public void setPackageDetails(Object packageDetails) {
        this.packageDetails = packageDetails;
    }

    public Object getPackageType() {
        return packageType;
    }

    public void setPackageType(Object packageType) {
        this.packageType = packageType;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }


    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public Object getPackageBuyKnow() {
        return packageBuyKnow;
    }

    public void setPackageBuyKnow(Object packageBuyKnow) {
        this.packageBuyKnow = packageBuyKnow;
    }

    public Object getPackageLife() {
        return packageLife;
    }

    public void setPackageLife(Object packageLife) {
        this.packageLife = packageLife;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getPackageTargetType() {
        return packageTargetType;
    }

    public void setPackageTargetType(int packageTargetType) {
        this.packageTargetType = packageTargetType;
    }

    public Object getPackageImage() {
        return packageImage;
    }

    public void setPackageImage(Object packageImage) {
        this.packageImage = packageImage;
    }

    public Object getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(Object packageWeight) {
        this.packageWeight = packageWeight;
    }

    public Object getPackageSales() {
        return packageSales;
    }

    public void setPackageSales(Object packageSales) {
        this.packageSales = packageSales;
    }


    public int getPackageTop() {
        return packageTop;
    }

    public void setPackageTop(int packageTop) {
        this.packageTop = packageTop;
    }

    public String getPackageVarieties() {
        return packageVarieties;
    }

    public void setPackageVarieties(String packageVarieties) {
        this.packageVarieties = packageVarieties;
    }
}
