package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/11.
 */

public class CostEntity implements Serializable {

    /**
     * costType : 1
     * price : 0
     * costTypeStr : 饲料肥料
     * costTime : 2017-07-10 21:55:38
     */
    /**
     * userFarmId : 0
     * detailsId : 1
     * costState : 2
     * userId : 4
     * productId : 0
     * costId : 169
     */

    private int costType;
    private Double price;
    private String costTypeStr;
    private String costTime;


    private int userFarmId;
    private int detailsId;
    private int costState;
    private int userId;
    private int productId;
    private int costId;

    public int getCostType() {
        return costType;
    }

    public void setCostType(int costType) {
        this.costType = costType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCostTypeStr() {
        return costTypeStr;
    }

    public void setCostTypeStr(String costTypeStr) {
        this.costTypeStr = costTypeStr;
    }

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }

    public int getUserFarmId() {
        return userFarmId;
    }

    public void setUserFarmId(int userFarmId) {
        this.userFarmId = userFarmId;
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public int getCostState() {
        return costState;
    }

    public void setCostState(int costState) {
        this.costState = costState;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCostId() {
        return costId;
    }

    public void setCostId(int costId) {
        this.costId = costId;
    }
}
