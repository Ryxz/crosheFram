package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */

public class FramLandInfo implements Serializable {
//  "landCode": null,
//          "landName": "肥西土地",
//          "landOtherPrice": null,
//          "landState": 1,
//          "manageMoney": "11",
//          "feedMoney": "11",
//          "drugMoney": "22",
//          "landId": 1,
//          "landTags": "黄瓜、土豆"
    private String landId;
    private String landLayerCode;
    private String farmId;
    private String landName;
    private List<ImgInfo> landImages;
    private String landType;
    private List<CanGrowInfo> landPastModels;
    private int landState;
    private String landTags;
    private String  landArea;
    private String landOutput;
    private String landRentArea;
    private String seedMoney;
    private String drugMoney;
    private String feedMoney;
    private String landMoney;
    private String manageMoney;
    private String landCode;
    private String landPrice;
    private String landOtherPrice;

    public List<CanGrowInfo> getLandPastModels() {
        return landPastModels;
    }

    public void setLandPastModels(List<CanGrowInfo> landPastModels) {
        this.landPastModels = landPastModels;
    }

    public String getLandId() {
        return landId;
    }

    public void setLandId(String landId) {
        this.landId = landId;
    }

    public String getLandLayerCode() {
        return landLayerCode;
    }

    public void setLandLayerCode(String landLayerCode) {
        this.landLayerCode = landLayerCode;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getLandName() {
        return landName;
    }

    public void setLandName(String landName) {
        this.landName = landName;
    }

    public List<ImgInfo> getLandImages() {
        return landImages;
    }

    public void setLandImages(List<ImgInfo> landImages) {
        this.landImages = landImages;
    }

    public String getLandType() {
        return landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    public int getLandState() {
        return landState;
    }

    public void setLandState(int landState) {
        this.landState = landState;
    }

    public String getLandTags() {
        return landTags;
    }

    public void setLandTags(String landTags) {
        this.landTags = landTags;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public String getLandOutput() {
        return landOutput;
    }

    public void setLandOutput(String landOutput) {
        this.landOutput = landOutput;
    }

    public String getLandRentArea() {
        return landRentArea;
    }

    public void setLandRentArea(String landRentArea) {
        this.landRentArea = landRentArea;
    }

    public String getSeedMoney() {
        return seedMoney;
    }

    public void setSeedMoney(String seedMoney) {
        this.seedMoney = seedMoney;
    }

    public String getDrugMoney() {
        return drugMoney;
    }

    public void setDrugMoney(String drugMoney) {
        this.drugMoney = drugMoney;
    }

    public String getFeedMoney() {
        return feedMoney;
    }

    public void setFeedMoney(String feedMoney) {
        this.feedMoney = feedMoney;
    }

    public String getLandMoney() {
        return landMoney;
    }

    public void setLandMoney(String landMoney) {
        this.landMoney = landMoney;
    }

    public String getManageMoney() {
        return manageMoney;
    }

    public void setManageMoney(String manageMoney) {
        this.manageMoney = manageMoney;
    }

    public String getLandCode() {
        return landCode;
    }

    public void setLandCode(String landCode) {
        this.landCode = landCode;
    }

    public String getLandPrice() {
        return landPrice;
    }

    public void setLandPrice(String landPrice) {
        this.landPrice = landPrice;
    }

    public String getLandOtherPrice() {
        return landOtherPrice;
    }

    public void setLandOtherPrice(String landOtherPrice) {
        this.landOtherPrice = landOtherPrice;
    }
}
