package com.croshe.farming.Entity;

/**
 * Created by ZPA on 2017/7/5.
 */
/*
                    "standardId":6,
                    "stock":100,
                    "standardDateTime":"2017-07-01 21:17:38",
                    "standardSubtitle":"1000",
                    "standardPrice":100,
                    "useArea":null,
                    "targetId":13,
                    "standardName":"20",
                    "standardUnit":"克",
                    "targetType":1

 */

public class ProductStands {

    String standardId;
    String standardPrice;
    String standardName;
    String stock;
    String check = "0";

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }


    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(String standardPrice) {
        this.standardPrice = standardPrice;
    }
}
