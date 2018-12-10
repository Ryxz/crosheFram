package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/5.
 */

public class CanGrowInfo implements Serializable {
//         "outCost": "1111",
//                 "pastId": 1,
//                 "outLan": "111",
//                 "yield": "11",
//                 "landState": 1,
//                 "leaseArea": 100,
//                 "landId": 1,
//                 "outNumber": "1111"

    private String  outCost;
    private String pastId;
    private String outLan;
    private String yield;
    private String landState;
    private String leaseArea;
    private String landId;
    private String outNumber;

    public String getOutCost() {
        return outCost;
    }

    public void setOutCost(String outCost) {
        this.outCost = outCost;
    }

    public String getPastId() {
        return pastId;
    }

    public void setPastId(String pastId) {
        this.pastId = pastId;
    }

    public String getOutLan() {
        return outLan;
    }

    public void setOutLan(String outLan) {
        this.outLan = outLan;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

    public String getLandState() {
        return landState;
    }

    public void setLandState(String landState) {
        this.landState = landState;
    }

    public String getLeaseArea() {
        return leaseArea;
    }

    public void setLeaseArea(String leaseArea) {
        this.leaseArea = leaseArea;
    }

    public String getLandId() {
        return landId;
    }

    public void setLandId(String landId) {
        this.landId = landId;
    }

    public String getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(String outNumber) {
        this.outNumber = outNumber;
    }
}
