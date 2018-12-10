package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */

public class ProductInfo implements Serializable {
    private int ifPublish;
    private String productKeys;
    private String productImages;
    private String productBigImage;
    private String productSubtype;
    private String matureImage;
    private int shopId;
    private String productDetails;
    private int useArea;
    private String productSubtitle;
    private String productSmallImage;
//    private String standardName;
    private String publishTime;
    private String productCode;
    private int productMonthCount;
    private int typeClass;
    private int productId;
    private int productState;
    private String productDateTime;
    private int typeType;
    private String productAddress;
    private int harvestType;
    private double productOldPrice;
    private double productPrice;
    private String productName;
    private int typeId;
    private String productLayerCode;
    private TypeInfo type;
    private String rate;
    private String count;
    private String goodRate;
    private boolean check = false;
    private String typeName;
    private int scNum = 1;
    private String standardName;

    private StandInfo standard;


    public StandInfo getStandard() {
        return standard;
    }

    public void setStandard(StandInfo standard) {
        this.standard = standard;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public int getScNum() {
        return scNum;
    }

    public void setScNum(int scNum) {
        this.scNum = scNum;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getGoodRate() {
        return goodRate;
    }

    public void setGoodRate(String goodRate) {
        this.goodRate = goodRate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
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

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages;
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

    public int getProductMonthCount() {
        return productMonthCount;
    }

    public void setProductMonthCount(int productMonthCount) {
        this.productMonthCount = productMonthCount;
    }

    public int getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(int typeClass) {
        this.typeClass = typeClass;
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

    public int getTypeType() {
        return typeType;
    }

    public void setTypeType(int typeType) {
        this.typeType = typeType;
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

    public TypeInfo getType() {
        return type;
    }

    public void setType(TypeInfo type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

