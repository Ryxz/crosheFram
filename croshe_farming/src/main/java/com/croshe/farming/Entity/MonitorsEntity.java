package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/12.
 */

public class MonitorsEntity implements Serializable {


    /**
     * min : null
     * unit : ℃
     * monitorId : 3206
     * max : null
     * updateTime : 2017-07-12 10:15:21
     * alert : 0
     * name : 温度
     * value : 30.4
     * alertdetails : 温度正常
     * deviceId : 2097
     */

    private String min;
    private String unit;
    private int monitorId;
    private String max;
    private String updateTime;
    private int alert;
    private String name;
    private double value;
    private String alertdetails;
    private int deviceId;

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(int monitorId) {
        this.monitorId = monitorId;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getAlert() {
        return alert;
    }

    public void setAlert(int alert) {
        this.alert = alert;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getAlertdetails() {
        return alertdetails;
    }

    public void setAlertdetails(String alertdetails) {
        this.alertdetails = alertdetails;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
}

