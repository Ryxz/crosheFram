package com.croshe.farming.Entity;

import com.croshe.crosheandroidbase.util.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */

/*
*       "countPackage":1,
        "packageModels":Array[1]
* */
public class Collocation implements Serializable{
    private List<PackageModel> packageModels;
    private String countPackage;

    public String getCountPackage() {
        if(StringUtils.isEmpty(countPackage)){
            return "0";
        }
        return countPackage;
    }

    public void setCountPackage(String countPackage) {
        this.countPackage = countPackage;
    }

    public List<PackageModel> getPackageModels() {
        return packageModels;
    }

    public void setPackageModels(List<PackageModel> packageModels) {
        this.packageModels = packageModels;
    }
}
