package com.croshe.farming.Entity;

/**
 * Created by Administrator on 2017/7/10.
 */

public class CollectionEntity {

    /**
     * collectionId : 1
     * productImg : attachments/productSmallImage/5684692b9cdd062a50df5de10fb1ceb0.jpg
     * userId : 4
     * collectionKey : null
     * targetId : 57
     * collectionDateTime : 2017-07-10 16:09:49
     * productPrice : 0.6
     * productName : 有机种植韭菜种籽
     * targetType : 1
     */

    private int collectionId;
    private String productImg;
    private int userId;
    private Object collectionKey;
    private int targetId;
    private String collectionDateTime;
    private double productPrice;
    private String productName;
    private int targetType;

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Object getCollectionKey() {
        return collectionKey;
    }

    public void setCollectionKey(Object collectionKey) {
        this.collectionKey = collectionKey;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getCollectionDateTime() {
        return collectionDateTime;
    }

    public void setCollectionDateTime(String collectionDateTime) {
        this.collectionDateTime = collectionDateTime;
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

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }
}
