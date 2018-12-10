package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/15.
 */

public class SignListEntity implements Serializable {


    /**
     * signId : 16
     * signTime : 2017-07-15 10:12:38
     * signDay : 1
     * signIp : null
     * signUserId : 4
     * signScore : 5
     */

    private int signId;
    private String signTime;
    private int signDay;
    private String signIp;
    private int signUserId;
    private int signScore;
    private int ifSign;
    /**
     * signIp : null
     * sumAllScore : 20
     * countAllDay : 4
     */

    private int sumAllScore;
    private int countAllDay;

    public int getIfSign() {
        return ifSign;
    }

    public void setIfSign(int ifSign) {
        this.ifSign = ifSign;
    }

    public int getSignId() {
        return signId;
    }

    public void setSignId(int signId) {
        this.signId = signId;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public int getSignDay() {
        return signDay;
    }

    public void setSignDay(int signDay) {
        this.signDay = signDay;
    }

    public String getSignIp() {
        return signIp;
    }

    public void setSignIp(String signIp) {
        this.signIp = signIp;
    }

    public int getSignUserId() {
        return signUserId;
    }

    public void setSignUserId(int signUserId) {
        this.signUserId = signUserId;
    }

    public int getSignScore() {
        return signScore;
    }

    public void setSignScore(int signScore) {
        this.signScore = signScore;
    }

    public int getSumAllScore() {
        return sumAllScore;
    }

    public void setSumAllScore(int sumAllScore) {
        this.sumAllScore = sumAllScore;
    }

    public int getCountAllDay() {
        return countAllDay;
    }

    public void setCountAllDay(int countAllDay) {
        this.countAllDay = countAllDay;
    }
}
