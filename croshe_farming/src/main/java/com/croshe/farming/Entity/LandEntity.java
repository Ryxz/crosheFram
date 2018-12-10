package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public class LandEntity implements Serializable{
    /**
     * landOutput : 12
     * landTypeStr : 种植
     * landArea : 1212
     * landMoney : 1元
     * farmId : 2
     * landRentArea : 1212
     * seedMoney : 0元
     * landPrice : 1212
     * landLayerCode : null
     * landTotalPrice : 1212
     * landType : 0
     * landCode : 00000000001
     * landName : 1号土地
     * landOtherPrice : 1212
     * landState : 0
     * manageMoney : 1元
     * feedMoney : 1元
     * drugMoney : 1元
     * landId : 1
     * landTags : 1212
     */

    private String landOutput;
    private String landTypeStr;
    private int landArea;
    private String landMoney;
    private int farmId;
    private int landRentArea;
    private String seedMoney;
    private int landPrice;
    private Object landLayerCode;
    private int landTotalPrice;
    private int landType;
    private String landCode;
    private String landName;
    private int landOtherPrice;
    private int landState;
    private String manageMoney;
    private String feedMoney;
    private String drugMoney;
    private int landId;
    private String landTags;
    private List<ImgInfo> landImages;

    public List<ImgInfo> getLandImages() {
        return landImages;
    }

    public void setLandImages(List<ImgInfo> landImages) {
        this.landImages = landImages;
    }

    public String getLandOutput() {
        return landOutput;
    }

    public void setLandOutput(String landOutput) {
        this.landOutput = landOutput;
    }

    public String getLandTypeStr() {
        return landTypeStr;
    }

    public void setLandTypeStr(String landTypeStr) {
        this.landTypeStr = landTypeStr;
    }

    public int getLandArea() {
        return landArea;
    }

    public void setLandArea(int landArea) {
        this.landArea = landArea;
    }

    public String getLandMoney() {
        return landMoney;
    }

    public void setLandMoney(String landMoney) {
        this.landMoney = landMoney;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public int getLandRentArea() {
        return landRentArea;
    }

    public void setLandRentArea(int landRentArea) {
        this.landRentArea = landRentArea;
    }

    public String getSeedMoney() {
        return seedMoney;
    }

    public void setSeedMoney(String seedMoney) {
        this.seedMoney = seedMoney;
    }

    public int getLandPrice() {
        return landPrice;
    }

    public void setLandPrice(int landPrice) {
        this.landPrice = landPrice;
    }

    public Object getLandLayerCode() {
        return landLayerCode;
    }

    public void setLandLayerCode(Object landLayerCode) {
        this.landLayerCode = landLayerCode;
    }

    public int getLandTotalPrice() {
        return landTotalPrice;
    }

    public void setLandTotalPrice(int landTotalPrice) {
        this.landTotalPrice = landTotalPrice;
    }

    public int getLandType() {
        return landType;
    }

    public void setLandType(int landType) {
        this.landType = landType;
    }

    public String getLandCode() {
        return landCode;
    }

    public void setLandCode(String landCode) {
        this.landCode = landCode;
    }

    public String getLandName() {
        return landName;
    }

    public void setLandName(String landName) {
        this.landName = landName;
    }

    public int getLandOtherPrice() {
        return landOtherPrice;
    }

    public void setLandOtherPrice(int landOtherPrice) {
        this.landOtherPrice = landOtherPrice;
    }

    public int getLandState() {
        return landState;
    }

    public void setLandState(int landState) {
        this.landState = landState;
    }

    public String getManageMoney() {
        return manageMoney;
    }

    public void setManageMoney(String manageMoney) {
        this.manageMoney = manageMoney;
    }

    public String getFeedMoney() {
        return feedMoney;
    }

    public void setFeedMoney(String feedMoney) {
        this.feedMoney = feedMoney;
    }

    public String getDrugMoney() {
        return drugMoney;
    }

    public void setDrugMoney(String drugMoney) {
        this.drugMoney = drugMoney;
    }

    public int getLandId() {
        return landId;
    }

    public void setLandId(int landId) {
        this.landId = landId;
    }

    public String getLandTags() {
        return landTags;
    }

    public void setLandTags(String landTags) {
        this.landTags = landTags;
    }
}
