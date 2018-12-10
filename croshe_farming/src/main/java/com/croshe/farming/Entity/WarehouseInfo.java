package com.croshe.farming.Entity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class WarehouseInfo {
//    "count":3,
//            "userStoreModels"

    private String count;
    private List<BasketInfo>  userStoreModels;

    public List<BasketInfo> getUserStoreModels() {
        return userStoreModels;
    }

    public void setUserStoreModels(List<BasketInfo> userStoreModels) {
        this.userStoreModels = userStoreModels;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }


}
