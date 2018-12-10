package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class OrderInfo implements Serializable {
    private List<OrderModels> orderModels;

    public List<OrderModels> getOrderModels() {
        return orderModels;
    }

    public void setOrderModels(List<OrderModels> orderModels) {
        this.orderModels = orderModels;
    }
}
