package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/17.
 */

public class CarEntity implements Serializable {


    /**
     * carId : 30
     * productCount : 2
     * countPrice : 20
     */

    private String carId;
    private String productCount;
    private Double countPrice;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public Double getCountPrice() {
        return countPrice;
    }

    public void setCountPrice(Double countPrice) {
        this.countPrice = countPrice;
    }
}
