package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/15.
 */

public class SignEntity implements Serializable {


    /**
     * signs
     * sumScore : 5
     * countDay : 1
     */
    private List<SignListEntity> signs;
    private int sumMonthScore;
    private int countMonthDay;

    public List<SignListEntity> getSigns() {
        return signs;
    }

    public void setSigns(List<SignListEntity> signs) {
        this.signs = signs;
    }

    public int getSumMonthScore() {
        return sumMonthScore;
    }

    public void setSumMonthScore(int sumMonthScore) {
        this.sumMonthScore = sumMonthScore;
    }

    public int getCountMonthDay() {
        return countMonthDay;
    }

    public void setCountMonthDay(int countMonthDay) {
        this.countMonthDay = countMonthDay;
    }
}
