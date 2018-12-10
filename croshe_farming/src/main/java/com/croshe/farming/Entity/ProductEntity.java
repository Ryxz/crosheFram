package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */

public class ProductEntity implements Serializable {

    /**
     * ifPublish : 1
     * productKeys : 种子,种子,种子
     * productBigImage : attachments/productBigImage/fd4a08d1b0f7b03062cad3aecf66e223.jpg
     * productSubtype : null
     * convertWeightUnit : null
     * convertNumberUnit : null
     * matureImage : attachments/matureImage/c940adb8117052ceccba650042cda964.jpg
     * shopId : 0
     * productDetails : null
     * useArea : 1
     * productSubtitle : 绿色种植木耳菜种籽
     * productSmallImage : attachments/productSmallImage/b642a312d2dd1b377b00ba4ac92456b3.jpg
     * liveDay : 30
     * convertNumber1 : null
     * convertNumber2 : null
     * publishTime : 2017-07-19 21:15:09
     * productCode : 00000000054
     * convertWeight1 : null
     * laborCost : 0.03
     * productMonthCount : 0
     * convertWeight2 : null
     * feiliaoCost : 0.01
     * productId : 54
     * productState : 0
     * productDateTime : 2017-07-04 16:14:55
     * manageCost : 0.01
     * productAddress : null
     * harvestType : 2
     * productOldPrice : 0.22
     * productPrice : 0.22
     * productName : 有机种植木耳菜种籽
     * typeId : 3
     * productLayerCode : null
     */

    private int ifPublish;
    private String productKeys;
    private String productBigImage;
    private String productSubtype;
    private String convertWeightUnit;
    private String convertNumberUnit;
    private String matureImage;
    private int shopId;
    private String productDetails;
    private int useArea;
    private String productSubtitle;
    private String productSmallImage;
    private int liveDay;
    private String convertNumber1;
    private String convertNumber2;
    private String publishTime;
    private String productCode;
    private String convertWeight1;
    private double laborCost;
    private int productMonthCount;
    private String convertWeight2;
    private double feiliaoCost;
    private int productId;
    private int productState;
    private String productDateTime;
    private double manageCost;
    private String productAddress;
    private int harvestType;
    private double productOldPrice;
    private double productPrice;
    private String productName;
    private int typeId;
    private String productLayerCode;
    private List<StandardNameEntity> standardName;

    public List<StandardNameEntity> getStandardName() {
        return standardName;
    }

    public void setStandardName(List<StandardNameEntity> standardName) {
        this.standardName = standardName;
    }

    public int getIfPublish() {
        return ifPublish;
    }

    public void setIfPublish(int ifPublish) {
        this.ifPublish = ifPublish;
    }

    public String getProductKeys() {
        return productKeys;
    }

    public void setProductKeys(String productKeys) {
        this.productKeys = productKeys;
    }

    public String getProductBigImage() {
        return productBigImage;
    }

    public void setProductBigImage(String productBigImage) {
        this.productBigImage = productBigImage;
    }

    public String getProductSubtype() {
        return productSubtype;
    }

    public void setProductSubtype(String productSubtype) {
        this.productSubtype = productSubtype;
    }

    public String getConvertWeightUnit() {
        return convertWeightUnit;
    }

    public void setConvertWeightUnit(String convertWeightUnit) {
        this.convertWeightUnit = convertWeightUnit;
    }

    public String getConvertNumberUnit() {
        return convertNumberUnit;
    }

    public void setConvertNumberUnit(String convertNumberUnit) {
        this.convertNumberUnit = convertNumberUnit;
    }

    public String getMatureImage() {
        return matureImage;
    }

    public void setMatureImage(String matureImage) {
        this.matureImage = matureImage;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public int getUseArea() {
        return useArea;
    }

    public void setUseArea(int useArea) {
        this.useArea = useArea;
    }

    public String getProductSubtitle() {
        return productSubtitle;
    }

    public void setProductSubtitle(String productSubtitle) {
        this.productSubtitle = productSubtitle;
    }

    public String getProductSmallImage() {
        return productSmallImage;
    }

    public void setProductSmallImage(String productSmallImage) {
        this.productSmallImage = productSmallImage;
    }

    public int getLiveDay() {
        return liveDay;
    }

    public void setLiveDay(int liveDay) {
        this.liveDay = liveDay;
    }

    public String getConvertNumber1() {
        return convertNumber1;
    }

    public void setConvertNumber1(String convertNumber1) {
        this.convertNumber1 = convertNumber1;
    }

    public String getConvertNumber2() {
        return convertNumber2;
    }

    public void setConvertNumber2(String convertNumber2) {
        this.convertNumber2 = convertNumber2;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getConvertWeight1() {
        return convertWeight1;
    }

    public void setConvertWeight1(String convertWeight1) {
        this.convertWeight1 = convertWeight1;
    }

    public double getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(double laborCost) {
        this.laborCost = laborCost;
    }

    public int getProductMonthCount() {
        return productMonthCount;
    }

    public void setProductMonthCount(int productMonthCount) {
        this.productMonthCount = productMonthCount;
    }

    public String getConvertWeight2() {
        return convertWeight2;
    }

    public void setConvertWeight2(String convertWeight2) {
        this.convertWeight2 = convertWeight2;
    }

    public double getFeiliaoCost() {
        return feiliaoCost;
    }

    public void setFeiliaoCost(double feiliaoCost) {
        this.feiliaoCost = feiliaoCost;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductState() {
        return productState;
    }

    public void setProductState(int productState) {
        this.productState = productState;
    }

    public String getProductDateTime() {
        return productDateTime;
    }

    public void setProductDateTime(String productDateTime) {
        this.productDateTime = productDateTime;
    }

    public double getManageCost() {
        return manageCost;
    }

    public void setManageCost(double manageCost) {
        this.manageCost = manageCost;
    }

    public String getProductAddress() {
        return productAddress;
    }

    public void setProductAddress(String productAddress) {
        this.productAddress = productAddress;
    }

    public int getHarvestType() {
        return harvestType;
    }

    public void setHarvestType(int harvestType) {
        this.harvestType = harvestType;
    }

    public double getProductOldPrice() {
        return productOldPrice;
    }

    public void setProductOldPrice(double productOldPrice) {
        this.productOldPrice = productOldPrice;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getProductLayerCode() {
        return productLayerCode;
    }

    public void setProductLayerCode(String productLayerCode) {
        this.productLayerCode = productLayerCode;
    }
}
