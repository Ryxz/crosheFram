package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/25.
 */

public class GoodsEntity  implements Serializable {
    private String image;//商品图片
    private String name;//商品详情 名称作用
    private String price;//商品价格
    private String evaluateCount;//商品评价的数量
    private String rate;//商品的好评率

    public String getEvaluateCount() {
        return evaluateCount;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getRate() {
        return rate;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setEvaluateCount(String evaluateCount) {
        this.evaluateCount = evaluateCount;
    }
}
