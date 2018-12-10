package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/5.
 */
//农业成本
public class FarmCostInfo implements Serializable {
//      "userFarmId": 1,
//              "costType": 1,
//              "price": 22,
//              "userId": 1,
//              "costTime": "2017-06-03 10:45:17",
//              "productId": 1,
//              "costId": 1
    private String userFarmId;
    private String costType;
    private String price;
    private String userId;
    private String costTime;
    private String productId;
    private String costId;

    public String getUserFarmId() {
        return userFarmId;
    }

    public void setUserFarmId(String userFarmId) {
        this.userFarmId = userFarmId;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }
}
